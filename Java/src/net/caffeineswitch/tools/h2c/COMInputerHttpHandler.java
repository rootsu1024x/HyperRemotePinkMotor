package net.caffeineswitch.tools.h2c;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class COMInputerHttpHandler implements HttpHandler {
	private static final byte[] SERVER_ERROR = "<html><head><title>500 - Error</title></head><body>500 - Error</body></html>"
			.getBytes();
	private static final byte[] SERVER_OK = "<html><head><title>200 - OK</title></head><body>200 - OK</body></html>"
			.getBytes();

	@Override
	public void handle(HttpExchange ex) throws IOException {
		OutputStream out = ex.getResponseBody();
		try {
			if (ex.getRequestMethod().equals("GET")) {
				String str = ex.getRequestURI().toString();
				if (str.length() >= 2 && str.length() <= 4) {
					int power = Integer.parseInt(str.substring(1, str.length()));
					System.out.println(power);
					COMInputer.input(power);
				}
				ex.sendResponseHeaders(200, SERVER_OK.length);
				out.write(SERVER_OK);
			} else {
				ex.sendResponseHeaders(500, SERVER_ERROR.length);
				out.write(SERVER_ERROR);
			}
		} catch (Exception e) {
			ex.sendResponseHeaders(500, SERVER_ERROR.length);
			out.write(SERVER_ERROR);
		} finally {
			out.close();
			ex.close();
		}
	}

}
