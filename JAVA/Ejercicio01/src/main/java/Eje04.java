

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Eje04
 */
@WebServlet("/Eje04")
public class Eje04 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eje04() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		 PrintWriter out = response.getWriter();
	        try {
	            out.println("<html>");
	            out.println("<head>");
	            Random rd = new Random();
	            int tabla[] = new int [20];
	            for (int i =0; i<tabla.length; i++) {
	            	// Entre 1 y 10
	            	tabla[i] = rd.nextInt(10)+1;
	            }
	            out.println("<table border='1'><tr>");
	            for (int i =0; i<tabla.length; i++) {
	            	out.print("<td>"+tabla[i]+"</td>");
	            }
	            
	            out.println("</tr></table>");
	            out.println("<br> Máximo ="+getMax(tabla));
	            out.println("<br> Mínimo ="+getMin(tabla));
	            out.println("<br> Moda   ="+getModa(tabla));
	            
	            out.println("</body></html>");
	        } finally {
				out.close();
			}
	}

	// Funciones auxiliares
	private int getMax ( int t[]) {
		int resu = t[0];
		for (int i=1; i< t.length; i++) {
			if (t[i]>resu) {
				resu = t[i];
			}
		}
		return resu;
		
	}
	
	private int getMin ( int t[]) {
		int resu = t[0];
		for (int i=1; i< t.length; i++) {
			if (t[i]<resu) {
				resu = t[i];
			}
		}
		return resu;
	}	
	
	private int getModa (int t[]) {
		int vmoda = 0;
		int fmoda = 0;
		int vmodatmp;
		int fmodatmp; 
		for (int i = 0; i < t.length; i++) {
			vmodatmp = t[i];
			fmodatmp = 1;
			for (int j = i+1; j < t.length; j++) {
				if (vmodatmp == t[j]) {
					fmodatmp++;
				}
			}
			if (fmodatmp > fmoda) {
				fmoda = fmodatmp;
				vmoda = vmodatmp;
			}
		}
		return vmoda;
	}
		
	
}
