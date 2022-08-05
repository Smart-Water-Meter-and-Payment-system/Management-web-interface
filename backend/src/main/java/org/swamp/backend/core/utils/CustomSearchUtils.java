package org.swamp.backend.core.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    
    public static Search generateSearchObjectForMeters(String query, SortField sortField, List<String> userIds, 
    		BigDecimal longitude, BigDecimal latitude, Date fromDate, Date toDate) {
//    	Search search = generateSearchTerms(query, Arrays.asList("countryName", "cityName", "publicIp"));
    	Search search = generateSearchTerms(query, Arrays.asList("userId.firstName", "userId.lastName"));
    	
        //searching for owners
        if(userIds != null && !userIds.isEmpty())
        	search.addFilterIn("userId.id", userIds);
        
        //searching for longitude
        if(longitude != null && longitude.compareTo(BigDecimal.ZERO)>0)
        	search.addFilterEqual("longitude", longitude);

        //searching for longitude
        if(latitude != null && latitude.compareTo(BigDecimal.ZERO)>0)
        	search.addFilterEqual("latitude", latitude);
        
        //fromDate <= x <= toDate
        //searching for from date
        if(fromDate != null)
        	search.addFilterGreaterOrEqual("dateCreated", fromDate);
        
        //searching for to date
        if(toDate != null)
        	search.addFilterLessOrEqual("dateCreated", toDate);
    	
    	return addSortField(sortField, search);
    }
    
    public static Search generateSearchObjectForCustomers(String query, SortField sortField, List<Meter> meters, 
    		BigDecimal householdBalance, Date fromDate, Date toDate) {
    	Search search = generateSearchTerms(query, Arrays.asList("phoneNumber", "token"));
    	
        //searching for meters
        if(meters != null && !meters.isEmpty())
        	search.addFilterIn("meterId", meters);
        
        //searching for householdBalance
        if(householdBalance != null && householdBalance.compareTo(BigDecimal.ZERO)>0)
        	search.addFilterEqual("householdBalance.balance", householdBalance);
        
        //fromDate <= x <= toDate
        //searching for from date
        if(fromDate != null)
        	search.addFilterGreaterOrEqual("dateCreated", fromDate);

        //searching for to date
        if(toDate != null)
        	search.addFilterLessOrEqual("dateCreated", toDate);
    	
    	return addSortField(sortField, search);
    }
    
    public static Search generateSearchObjectForWaterChargeRate(String query, SortField sortField, 
    		List<ChargeRateStatus> chargeRateStatus, BigDecimal waterVolume, BigDecimal charge) {
    	Search search = generateSearchTerms(query, Arrays.asList(""));

    	//searching for chargeRates
    	if(chargeRateStatus != null && !chargeRateStatus.isEmpty())
    		search.addFilterIn("activated", chargeRateStatus);
    	
    	//searching for water volume
        if(waterVolume != null && waterVolume.compareTo(BigDecimal.ZERO)>0)
        	search.addFilterEqual("waterVolume", waterVolume);
        
        //searching for charge
        if(charge != null && charge.compareTo(BigDecimal.ZERO)>0)
        	search.addFilterEqual("charge", charge);
    	
    	return addSortField(sortField, search);
    }
    
    public static Search generateSearchObjectForTransactionRecord(String query, SortField sortField, 
    		BigDecimal waterVolumeCollected, BigDecimal amountPaid) {
    	Search search = generateSearchTerms(query, Arrays.asList(""));
    	
    	//searching for water volume collected
        if(waterVolumeCollected != null && waterVolumeCollected.compareTo(BigDecimal.ZERO)>0)
        	search.addFilterEqual("waterVolumeCollected", waterVolumeCollected);

        //searching for amount paid
        if(amountPaid != null && amountPaid.compareTo(BigDecimal.ZERO)>0)
        	search.addFilterEqual("amountPaid", amountPaid);
    	
    	return addSortField(sortField, search);
    }

}

