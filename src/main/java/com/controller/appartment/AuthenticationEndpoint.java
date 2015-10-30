package com.controller.appartment;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.math.BigInteger;
import java.security.Key;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.dao.apartment.impl.UserDaoImpl;
import com.devi.miscellnious.SendMail;
import com.model.appartment.User;
import com.pojo.apartment.ActivationPojo;
import com.pojo.apartment.Credentials;
import com.pojo.apartment.Userdetails;

@Path("/auth")
public class AuthenticationEndpoint {
	@POST
	@Path("/signup")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getMsg(Userdetails userpojo) {
		String json = "";
		JSONObject obj = new JSONObject();
		UUID idOne = UUID.randomUUID();

		try {
			if (!checkUserExists(userpojo.getEmail())) {

				obj.put("name", userpojo.getUserName());

				// obj.put("uvalue", String.valueOf(idOne));
				json = obj.toString();
				adduser(userpojo, String.valueOf(idOne));
				new SendMail(userpojo.getEmail(), String.valueOf(idOne));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(json)
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
				.build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(Credentials credentials) {
		System.out.println(credentials.getUsername());
		System.out.println(credentials.getPassword());
		try {

			if (authenticate(credentials.getUsername(),
					credentials.getPassword())) {

				JSONObject obj = new JSONObject();
				obj.put("name", credentials.getUsername());
				return Response
						.ok(obj.toString())
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods",
								"GET, POST, OPTIONS").build();
			}

		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}
		return Response.status(Response.Status.UNAUTHORIZED).build();
	}

	@POST
	@Path("/activation")
	public Response authorizeUserID(ActivationPojo activationPojo) {
		System.out.println(activationPojo.getUmail());
		try {
			if (activateUser(activationPojo.getUmail(),activationPojo.getaId())) {
				JSONObject obj = new JSONObject();
				obj.put("name", activationPojo.getUmail());
				return Response
						.ok(obj.toString())
						.header("Access-Control-Allow-Origin", "*")
						.header("Access-Control-Allow-Methods",
								"GET, POST, OPTIONS").build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.UNAUTHORIZED)
					.entity("unuthorized user pls signup again").build();
		}
		return Response.status(Response.Status.UNAUTHORIZED)
				.entity("unuthorized user pls signup again").build();
	}

	private boolean authenticate(String username, String password)
			throws Exception {

		return new UserDaoImpl().userLogin(username, password);
	}

	private String issueToken(String username) {
		Key key = MacProvider.generateKey();

		return Jwts.builder().setSubject(username)
				.signWith(SignatureAlgorithm.HS512, key).compact();

	}

	private void adduser(Userdetails userpojo, String randomValue) {
		Session session = SessionFactoryapp.getSessionFactory().openSession();
		session.beginTransaction();
		User dbuser = new User();
		dbuser.setFirstname(userpojo.getUserName());
		dbuser.setLastname(userpojo.getLname());
		dbuser.setCity("Banglore");
		dbuser.setEmailid(userpojo.getEmail());
		dbuser.setPhonenumber(new BigInteger(userpojo.getTelnum()));
		dbuser.setPassword(userpojo.getPwd());
		dbuser.setActivation(String.valueOf(randomValue));
		session.save(dbuser);
		session.getTransaction().commit();
	}

	private boolean checkUserExists(String emailid) {
		Session session = SessionFactoryapp.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("emailid", emailid));
		crit.setProjection(Projections.rowCount());
		Long count = (Long) crit.uniqueResult();
		System.out.println("result is " + count);

		return count <= 0 ? false : true;
	}

	private boolean activateUser(String emailid, String activationid) {
		System.out.println("emailid passed is " + emailid);
		Session session = SessionFactoryapp.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(User.class);
		crit.add(Restrictions.eq("emailid", emailid));
		crit.setProjection(Projections.property("activation"));
		System.out.println("result " + crit.uniqueResult().toString());
		return crit.uniqueResult().toString().equals(activationid);
	}
}
