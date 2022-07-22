package org.swamp.backend.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.sers.webutils.model.RecordStatus;
import org.sers.webutils.model.utils.SortField;
import org.swamp.backend.constants.ChargeRateStatus;
import org.swamp.backend.models.meter.Meter;

import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.Sort;

public class CustomSearchUtils {

    private static final int MINIMUM_CHARACTERS_FOR_SEARCH_TERM = 1;

    public static boolean searchTermSatisfiesQueryCriteria(String query) {
        if (StringUtils.isBlank(query)) {
            return false;
        }
        return query.length() >= MINIMUM_CHARACTERS_FOR_SEARCH_TERM;
    }

    private static Search generateSearchTerms(String query, List<String> searchFields) {
        Search search = new Search();
        search.addFilterEqual("recordStatus", RecordStatus.ACTIVE);

        if (StringUtils.isNotBlank(query) && CustomSearchUtils.searchTermSatisfiesQueryCriteria(query)) {
            ArrayList<Filter> filters = new ArrayList<Filter>();
            CustomSearchUtils.generateSearchTerms(searchFields, query, filters);
            search.addFilterAnd(filters.toArray(new Filter[filters.size()]));
        }
        return search;
    }
    
    private static boolean generateSearchTerms(List<String> searchFields, String query, List<Filter> filters) {
        if (searchFields != null && !searchFields.isEmpty()) {
            for (String token : query.replaceAll("  ", " ").split(" ")) {
                String searchTerm = "%" + StringEscapeUtils.escapeSql(token) + "%";
                Filter[] orFilters = new Filter[searchFields.size()];
                int counter = 0;
                for (String searchField : searchFields) {
                    orFilters[counter] = Filter.like(searchField, searchTerm);
                    counter++;
                }
                filters.add(Filter.or(orFilters));
            }
            return true;
        }
        return false;
    }
    
    public static Search addSortField(SortField sortField, Search search) {
    	if (sortField != null) {
			search.addSort(sortField.getSort());
		} else {
			search.addSort(new Sort("dateCreated", true));
		}

		return search;
    }

    public static Search generateSearchObjectForUsers(String query, SortField sortField, List<String> roleIds) {
        Search search = generateSearchTerms(query, Arrays.asList("username", "firstName", "lastName", "emailAddress"));
        
        //searching for user permission
        if(roleIds != null && !roleIds.isEmpty())
        	search.addFilterSome("roles", Filter.in("id", roleIds));
        
		return addSortField(sortField, search);
    }
    
    public static Search generateSearchObjectForRoles(String query, SortField sortField) {
        Search search = generateSearchTerms(query, Arrays.asList("name", "description"));
		return addSortField(sortField, search);
    }
    
    public static Search generateSearchObjectForMeters(String query, SortField sortField, List<String> userIds) {
    	Search search = generateSearchTerms(query, Arrays.asList("countryName", "cityName"));
    	
        //searching for owners
        if(userIds != null && !userIds.isEmpty())
        	search.addFilterIn("userId.id", userIds);
    	
    	return addSortField(sortField, search);
    }
    
    public static Search generateSearchObjectForCustomers(String query, SortField sortField, List<Meter> meters) {
    	Search search = generateSearchTerms(query, Arrays.asList("phoneNumber"));
    	
        //searching for meters
        if(meters != null && !meters.isEmpty())
        	search.addFilterIn("meter.id", meters);
    	
    	return addSortField(sortField, search);
    }
    
    public static Search generateSearchObjectForWaterChargeRate(String query, SortField sortField, 
    		List<ChargeRateStatus> chargeRateStatus) {
    	Search search = generateSearchTerms(query, Arrays.asList(""));

    	//searching for chargeRates
    	if(chargeRateStatus != null && !chargeRateStatus.isEmpty())
    		search.addFilterIn("activated", chargeRateStatus);
    	
    	return addSortField(sortField, search);
    }
    
    public static Search generateSearchObjectForTransactionRecord(String query, SortField sortField) {
    	Search search = generateSearchTerms(query, Arrays.asList(""));
    	
    	return addSortField(sortField, search);
    }
    
//    public static Search generateSearchForIntegers(Search search, List<String> selectedIntColumns, int min, int max) {
//    	if(selectedIntColumns != null) {
//    		if(selectedIntColumns.size() > 0) {
//    			for(String name : selectedIntColumns) {
//    				search.addFilterAnd(Filter.lessOrEqual(name, min), Filter.greaterOrEqual(name, max));
//    			}
//    		} else {
//    			selectedIntColumns = Arrays.asList("eloe", "estimatedCostAssignee", "estimatedCostOrganisation",
//    					"actualLoe", "actualCostOrganisation", "actualCostAssignee");
//    			for(String name : selectedIntColumns) {
//    				search.addFilterAnd(Filter.lessOrEqual(name, min), Filter.greaterOrEqual(name, max));
//    			}
//    		}
//    	}
//    	return search;
//    }


}

