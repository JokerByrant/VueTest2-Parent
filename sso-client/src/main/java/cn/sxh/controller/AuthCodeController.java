package cn.sxh.controller;

import cn.sxh.pojo.ResultPojo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @Description:
 * @author: sxh
 * @Date: 2018/10/11
 */
@RestController
@RequestMapping("/auth")
public class AuthCodeController {
    private char[] codeSequence = { 'A', '1','B', 'C', '2','D','3', 'E','4', 'F', '5','G','6', 'H', '7','I', '8','J',
            'K',   '9' ,'L', '1','M',  '2','N',  'P', '3', 'Q', '4', 'R', 'S', 'T', 'U', 'V', 'W',
            'X', 'Y', 'Z'};
    @RequestMapping("/code")
    public void getCode(HttpServletResponse response, HttpSession session) throws IOException {
        int width = 80;
        int height = 35;
        Random random = new Random();
        //设置response头信息
        //禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        //生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        //产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        //Graphics类的样式
        g.setColor(this.getColor(200, 250));
        g.setFont(new Font("Times New Roman",0,28));
        g.fillRect(0, 0, width, height);
        //绘制干扰线
        for(int i=0;i<40;i++){
            g.setColor(this.getColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        //绘制字符
//        String strCode = "";
        StringBuffer strCode = new StringBuffer();
        int [] ArrayList = {8, 25, 40, 58};
        for(int i=0;i<4;i++){
            String rand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            strCode.append(rand);
//            strCode = strCode + rand;
            g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
            if (i == 0) {
                g.drawString(rand, ArrayList[0], 28);
            } else if (i == 1) {
                g.drawString(rand, ArrayList[1], 28);
            } else if (i == 2) {
                g.drawString(rand, ArrayList[2], 28);
            } else if (i == 3) {
                g.drawString(rand, ArrayList[3], 28);
            }
        }
        //将字符保存到session中用于前端的验证
        session.setAttribute("authCode", strCode.toString().toLowerCase());
        g.dispose();

        ImageIO.write(image, "JPEG", response.getOutputStream());
        response.getOutputStream().flush();
    }

    public  Color getColor(int fc,int bc){
        Random random = new Random();
        if(fc>255)
            fc = 255;
        if(bc>255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }

    @RequestMapping("/checkCode")
    public ResultPojo checkCode(HttpServletRequest request, String code) throws IOException {
        ResultPojo pojo = new ResultPojo();
        // 验证验证码
        try {
            String sessionCode = request.getSession().getAttribute("authCode").toString();
            if (code != null && !"".equals(code) && sessionCode != null && !"".equals(sessionCode)) {
                if (code.equalsIgnoreCase(sessionCode)) {
//                response.getWriter().println("验证通过！");
                    pojo.setRetMessage("验证通过！");
                } else {
//                response.getWriter().println("验证失败！");
                    pojo.setRetMessage("验证失败！");
                    pojo.setRetCode("500");
                }
            } else {
//            response.getWriter().println("验证失败！");
                pojo.setRetMessage("验证失败！");
                pojo.setRetCode("500");
            }
        } catch (Exception e) {
//            log.error(e.getStackTrace() + "");
            e.printStackTrace();
            pojo.setRetMessage("验证码失效！");
            pojo.setRetCode("500");
        }
        return pojo;
    }
}
