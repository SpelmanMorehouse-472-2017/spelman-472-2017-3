package com.google.training.helloworld;

import java.util.*;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryOptions;
import com.google.cloud.bigquery.FieldValue;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobId;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.QueryResponse;
import com.google.cloud.bigquery.QueryResult;
import com.google.api.server.spi.config.Named;

/**
 * Defines endpoint functions APIs.
 */
@Api(name = "recipesendpoints", version = "v1",
scopes = {Constants.EMAIL_SCOPE },
        clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID },
        description = "API for recipes endpoints.")

public class RecipesEndpoints {

	 @ApiMethod(name = "getRecipeType", path = "getRecipeType", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getDessertRecipes(@Named("dessertrecipe") ArrayList<String> dessertrecipe) throws Exception {
		String recipe = "'Dessert'";
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT calories"
	    	    		+ " FROM `spelman-472-2017-3.Recipes.finalrecipes` WHERE calories=462").setUseLegacySql(false).build();
//    		+ " FROM `spelman-472-2017-3.Recipes.finalrecipes` WHERE categories=" + recipe + ";").setUseLegacySql(false).build();

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
	    		  dessertrecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(dessertrecipe);
	    }
   
	// Declare this method as a method available externally through Endpoints
    @ApiMethod(name = "sayHello", path = "sayHello",
            httpMethod = HttpMethod.GET)

    public RecipesClass sayHello() {
        return new RecipesClass();
    }
    
    // Declare this method as a method available externally through Endpoints
    @ApiMethod(name = "sayHelloByName", path = "sayHelloByName",
            httpMethod = HttpMethod.GET)

    public RecipesClass sayHelloByName (@Named("name") String name) {
        return new RecipesClass(name);
    }
}