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


public class IntegrationHelper extends junit.framework.TestCase {
    private volatile org.osgi.framework.BundleContext m_context;

    protected void setUp() throws Exception {
        m_context = org.osgi.framework.FrameworkUtil.getBundle(getClass()).getBundleContext();
    }
}
@Category(IntegrationHelper.class)
public class RecipeEndpointsTest {
    private RecipeEndpoints recipeEndpoints;
	
	@Before
	public void setUp() throws Exception{
		recipeEndpoints = new RecipeEndpoints();
	}

    public void testCategories() throws Exception {
        RecipeClass food = recipeEndpoints.getBreakfast()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getLunch()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getDinner()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getQuickEasy()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getGlutenFree()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getVegetarian()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getFallRecipe()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getSpringRecipe()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getSummer()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getFrozen()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getLowSugar()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getCakeRecipe()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
		
		food = recipeEndpoints.getDirections()

        Assert.assertNotNull(food);
		Assert.assertNotNull(queryJob);
        Assert.assertEquals(food, RecipeClass);
    }
}