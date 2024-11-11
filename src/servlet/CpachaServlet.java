package servlet;

import util.CpachaUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CpachaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public CpachaServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String method = request.getParameter("method");
		if("loginCapcha".equals(method)){
			CpachaUtil cpachaUtil = new CpachaUtil();
			String generatorVcode = cpachaUtil.generatorVCode();
			request.getSession().setAttribute("loginCapcha", generatorVcode);
			BufferedImage img = cpachaUtil.generatorVCodeImage(generatorVcode, true);
			ImageIO.write(img, "gif",response.getOutputStream());
			return;
		}
		response.getWriter().write("error method");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
