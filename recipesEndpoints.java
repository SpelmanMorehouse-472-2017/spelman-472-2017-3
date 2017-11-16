package com.google.devrel.training.conference;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.google.api.server.spi.config.Named;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryResponse;
import com.google.cloud.bigquery.QueryResult;
import com.google.devrel.training.conference.domain.Profile;
import com.google.devrel.training.conference.form.ProfileForm;

import static com.google.devrel.training.conference.service.OfyService.ofy;

import java.util.List;
import java.util.UUID;
/**
 * Defines endpoint functions APIs.
 */
@Api(name = "recipesendpoints", version = "v1", scopes = { Constants.EMAIL_SCOPE }, clientIds = {
		Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID }, description = "API for recipes endpoints.")

public class recipesEndpoints {

	private static String extractDefaultDisplayNameFromEmail(String email) {
		return email == null ? null : email.substring(0, email.indexOf("@"));
	}
	// Declare this method as a method available externally through Endpoints
	@ApiMethod(name = "getRecipe", path = "getRecipe", httpMethod = HttpMethod.GET)

	public recipesClass getDessertRecipes(@Named("dessertrecipe") String dessertrecipe) throws Exception {

		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	
	QueryJobConfiguration queryConfig =
		    QueryJobConfiguration.newBuilder(
		            "SELECT "
		                + "title"
		                + "FROM `spelman-472-2017-3:Recipes.finalrecipes` WHERE Team='Dessert';").setUseLegacySql(false).build();

		// Create a job ID so that we can safely retry.
		JobId jobId = JobId.of(UUID.randomUUID().toString());
		Job queryJob = bigquery.create(JobInfo.newBuilder(queryConfig).setJobId(jobId).build());

		// Wait for the query to complete.
		queryJob = queryJob.waitFor();

		// Check for errors
		if (queryJob == null) {
		  throw new RuntimeException("Job no longer exists");
		} else if (queryJob.getStatus().getError() != null) {
		  // You can also look at queryJob.getStatus().getExecutionErrors() for all
		  // errors, not just the latest one.
		  throw new RuntimeException(queryJob.getStatus().getError().toString());
		}

		// Get the results.
		QueryResponse response = bigquery.getQueryResults(jobId);
		QueryResult result = response.getResult();

		// Print all pages of the results.
		while (result != null) {
		  for (List<FieldValue> row : result.iterateAll()) {
		    List<FieldValue> titles = row.get(0).getRepeatedValue();
		    System.out.println("titles:");

		    for (FieldValue titleValue : titles) {
		      List<FieldValue> titleRecord = titleValue.getRecordValue();
		      String title = titleRecord.get(0).getStringValue();
		      long uniqueWords = titleRecord.get(1).getLongValue();
		      System.out.printf("\t%s: %d\n", title, uniqueWords);
		    }

		    long uniqueWords = row.get(1).getLongValue();
		    System.out.printf("total unique words: %d\n", uniqueWords);
		  }

		  result = result.getNextPage();
		}
		
		return new  recipesClass();
}
	// TODO 1 Pass the ProfileForm parameter
		// TODO 2 Pass the User parameter
		public Profile saveProfile(final User user, ProfileForm profileForm)
				throws UnauthorizedException {

			String userId = "";
			String mainEmail = "";
			String displayName = "Your name will go here";

			// TODO 2
			// If the user is not logged in, throw an UnauthorizedException
			if (user == null) {
	            throw new UnauthorizedException("Authorization required");
	        }

		

	        // Set the displayName to the value sent by the ProfileForm, if sent
	        // otherwise set it to null
			displayName = profileForm.getDisplayName();
			
			// Get the userId and mainEmail
			mainEmail = user.getEmail();
			userId = user.getUserId();

	        // If the displayName is null, set it to the default value based on the user's email
	        // by calling extractDefaultDisplayNameFromEmail(...)
			 if (displayName == null) {
			   displayName = extractDefaultDisplayNameFromEmail(user.getEmail());
			   }

			// Create a new Profile entity from the
			// userId, displayName, mainEmail and 
			Profile profile = new Profile(userId, displayName, mainEmail);

			// Save the entity in the datastore
			ofy().save().entity(profile).now();

			// Return the profile
			return profile;
		}
	// Declare this method as a method available externally through Endpoints
	@ApiMethod(name = "sayHelloByName", path = "sayHelloByName", httpMethod = HttpMethod.GET)

	public recipesClass sayHelloByName(@Named("name") String name) {
		return new recipesClass(name);
	}
}
