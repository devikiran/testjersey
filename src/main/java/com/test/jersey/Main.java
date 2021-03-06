package com.test.jersey;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Main {

  public static void main(String[] args) {
	  String webPort = System.getenv("PORT");
      if (webPort == null || webPort.isEmpty()) {
          webPort = "8080";
      }

      final Server server = new Server(Integer.valueOf(webPort));
      final WebAppContext root = new WebAppContext();

      root.setContextPath("/");
      // Parent loader priority is a class loader setting that Jetty accepts.
      // By default Jetty will behave like most web containers in that it will
      // allow your application to replace non-server libraries that are part of the
      // container. Setting parent loader priority to true changes this behavior.
      // Read more here: http://wiki.eclipse.org/Jetty/Reference/Jetty_Classloading
      root.setParentLoaderPriority(true);

      final String webappDirLocation = "src/main/webapp/";
      root.setDescriptor(webappDirLocation + "/WEB-INF/web.xml");
      root.setResourceBase(webappDirLocation);

      server.setHandler(root);

      try {
		server.start();
		server.join();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
    /*port(Integer.valueOf(System.getenv("PORT")));
    staticFileLocation("/public");

    get("/hello", (req, res) -> "Hello World");

    get("/", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("message", "Hello World!");

            return new ModelAndView(attributes, "index.ftl");
        }, new FreeMarkerEngine());

    get("/db", (req, res) -> {
      Connection connection = null;
      Map<String, Object> attributes = new HashMap<>();
      try {
        connection = DatabaseUrl.extract().getConnection();

        Statement stmt = connection.createStatement();
        stmt.executeUpdate("CREATE TABLE IF NOT EXISTS ticks (tick timestamp)");
        stmt.executeUpdate("INSERT INTO ticks VALUES (now())");
        ResultSet rs = stmt.executeQuery("SELECT tick FROM ticks");

        ArrayList<String> output = new ArrayList<String>();
        while (rs.next()) {
          output.add( "Read from DB: " + rs.getTimestamp("tick"));
        }

        attributes.put("results", output);
        return new ModelAndView(attributes, "db.ftl");
      } catch (Exception e) {
        attributes.put("message", "There was an error: " + e);
        return new ModelAndView(attributes, "error.ftl");
      } finally {
        if (connection != null) try{connection.close();} catch(SQLException e){}
      }
    }, new FreeMarkerEngine());*/

  }

}
