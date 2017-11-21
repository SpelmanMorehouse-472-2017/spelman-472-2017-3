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
	
	 @ApiMethod(name = "getBreakfast", path = "getBreakfast", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getBreakfastRecipes(@Named("breakfastrecipe") ArrayList<String> breakfastrecipe) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories =  'Breakfast' LIMIT 25").setUseLegacySql(true).build();
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
	    		  breakfastrecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(breakfastrecipe);
	    }
	
	 @ApiMethod(name = "getLunch", path = "getLunch", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getLunchRecipes(@Named("lunchrecipe") ArrayList<String> lunchrecipe) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Lunch' LIMIT 25").setUseLegacySql(true).build();
//		+ " FROM `spelman-472-2017-3.Recipes.finalrecipes` WHERE categories=" + recipe + ";").setUseLegacySql(false).build();

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
	    		  lunchrecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(lunchrecipe);
	    }
	 
	 @ApiMethod(name = "getDinner", path = "getDinner", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getDinnerRecipes(@Named("dinnerrecipe") ArrayList<String> dinnerrecipe) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Dinner' LIMIT 25").setUseLegacySql(true).build();
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
	    		  dinnerrecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(dinnerrecipe);
	    }
	 
	 @ApiMethod(name = "getQuickEasy", path = "getQuick&Easy", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getQuickEasyRecipes(@Named("quickrecipe") ArrayList<String> quickrecipe) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Quick & Easy' LIMIT 25").setUseLegacySql(true).build();
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
	    		  quickrecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(quickrecipe);
	    }
	 
	 @ApiMethod(name = "getGlutenFree", path = "getGlutenFree", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getGlutenFreeRecipes(@Named("quickrecipe") ArrayList<String> glutenrecipe) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Wheat/Gluten-Free' LIMIT 25").setUseLegacySql(true).build();
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
	    		  glutenrecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(glutenrecipe);
	    }
	 
	 @ApiMethod(name = "getVegetarianRecipe", path = "getVegetarianRecipe", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getVegetarianRecipes(@Named("vegitarianrecipe") String vegitarianrecipe) throws Exception {
			BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
		    	QueryJobConfiguration queryConfig =
		    	    QueryJobConfiguration.newBuilder(
		    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Vegetarian' LIMIT 25").setUseLegacySql(true).build();
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
		    	ArrayList<String> vegetarianrecipelist = new ArrayList<String>();
		    	while (result != null) {
		    	  for (List<FieldValue> row : result.iterateAll()) {
		    		  vegetarianrecipelist.add(row.get(0).getStringValue());
		    	  }

		    	  result = result.getNextPage();
		    	}
		    	
		    	return new RecipesClass(vegitarianrecipe,vegetarianrecipelist);
		    }
	  
	 @ApiMethod(name = "getFallRecipe", path = "getFallRecipe", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getFallRecipes(@Named("fallrecipe") String fallrecipe) throws Exception {
			BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
		    	QueryJobConfiguration queryConfig =
		    	    QueryJobConfiguration.newBuilder(
		    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Fall' LIMIT 25").setUseLegacySql(true).build();
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
		    	ArrayList<String> fallrecipelist = new ArrayList<String>();
		    	while (result != null) {
		    	  for (List<FieldValue> row : result.iterateAll()) {
		    		  fallrecipelist.add(row.get(0).getStringValue());
		    	  }

		    	  result = result.getNextPage();
		    	}
		    	
		    	return new RecipesClass(fallrecipe,fallrecipelist);
		    }
	 
	 @ApiMethod(name = "getSpringFree", path = "getSpringFree", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getSpringRecipes(@Named("quickrecipe") ArrayList<String> springrecipe) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Spring' LIMIT 25").setUseLegacySql(true).build();
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
	    		  springrecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(springrecipe);
	    }
	 
	 @ApiMethod(name = "getSummerRecipe", path = "geSummerRecipe", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getSummerRecipes(@Named("fallrecipe") String summerrecipe) throws Exception {
			BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
		    	QueryJobConfiguration queryConfig =
		    	    QueryJobConfiguration.newBuilder(
		    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Summer' LIMIT 25").setUseLegacySql(true).build();
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
		    	ArrayList<String> summerreciperecipelist = new ArrayList<String>();
		    	while (result != null) {
		    	  for (List<FieldValue> row : result.iterateAll()) {
		    		  summerreciperecipelist.add(row.get(0).getStringValue());
		    	  }

		    	  result = result.getNextPage();
		    	}
		    	
		    	return new RecipesClass(summerrecipe,summerreciperecipelist);
		    }
	 
	 @ApiMethod(name = "getFrozen", path = "getFrozen", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getFrozenRecipes(@Named("quickrecipe") ArrayList<String> frozenrecipe) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Frozen Dessert' LIMIT 25").setUseLegacySql(true).build();
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
	    		  frozenrecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(frozenrecipe);
	    }
	 
	 @ApiMethod(name = "getLowSugar", path = "getLowSugar", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getLowSugarRecipes(@Named("quickrecipe") ArrayList<String> nosugarrecipe) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Low/No Sugar' LIMIT 25").setUseLegacySql(true).build();
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
	    		  nosugarrecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(nosugarrecipe);
	    }
	 
	 @ApiMethod(name = "getCakeRecipe", path = "getCakeRecipe", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getDessertRecipes(@Named("dessertrecipe") ArrayList<String> dessertrecipe) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT title FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE categories = 'Cake' LIMIT 25").setUseLegacySql(true).build();
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
	 
	 @ApiMethod(name = "getDirections", path = "getDirections", 
	    		httpMethod = HttpMethod.GET)
	 public RecipesClass getDirections(@Named("title") String title) throws Exception {
		BigQuery bigquery = BigQueryOptions.getDefaultInstance().getService();
	    	String directions1 = "'" + title + " '";
		QueryJobConfiguration queryConfig =
	    	    QueryJobConfiguration.newBuilder(
	    	    		"SELECT directions FROM [spelman-472-2017-3:Recipes.finalrecipes] WHERE title =" + directions1 + " LIMIT 25").setUseLegacySql(true).build();
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
	    	ArrayList<String> directionsRecipe = new ArrayList<String>();
	    	while (result != null) {
	    	  for (List<FieldValue> row : result.iterateAll()) {
	    		  directionsRecipe.add(row.get(0).getStringValue());
	    	  }

	    	  result = result.getNextPage();
	    	}
	    	
	    	return new RecipesClass(title,directionsRecipe);
	    }
}
