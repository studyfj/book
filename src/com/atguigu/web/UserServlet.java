package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

/**
 * @Author Feng Jun
 * @Email fj13464498017@163.com
 * @Date 2021/07/29 9:40
 * @Version 1.0
 * @Description 致敬大师，致敬未来的自己
 */
public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 销毁session
        req.getSession().invalidate();
        // 重定向
        resp.sendRedirect(req.getContextPath());

    }
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        User user = userService.loginUser(WebUtils.copyParamToBean(req.getParameterMap(), new User()));
        if (user == null) {
            req.setAttribute("msg","用户名或密码错误!");
            req.setAttribute("username",username);
            System.out.println("登录失败，没有此用户!");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }else {
            System.out.println("登录成功！");

            // 保存用户登陆后的信息到session域
            req.getSession().setAttribute("user",user);

            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }

        System.out.println("处理登录需求。");
    }
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 Session 中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        // 删除 Session 中的验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        // 获取前台参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        // 检验验证码
        if (token != null && token.equalsIgnoreCase(code)) {
            // 1.检查用户名是否正确
            if (userService.existsUsername(username)) {
                System.out.println("用户名["+username+"]不可用！");

                req.setAttribute("msg","用户名已存在!");
                req.setAttribute("username",username);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);

            }else {
                // 可用，调service保存
                userService.registerUser(user);

                // 跳转成功页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);

            }
        }else {
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);
            // 不正确，跳回注册页面
            System.out.println("验证码："+code+"错误。");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }

    protected void ajaxExistsUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      // 获取请求参数username
        String username = req.getParameter("username");
        // 调用userService.existUsername()
        boolean existsUsername = userService.existsUsername(username);
        // 返回的结果回传客户端
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("existsUsername", existsUsername);
        Gson gson = new Gson();
        String s = gson.toJson(resultMap);
        resp.getWriter().write(s);

    }


}
