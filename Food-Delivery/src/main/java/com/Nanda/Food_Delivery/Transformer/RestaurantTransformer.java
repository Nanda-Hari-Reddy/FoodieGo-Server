package com.Nanda.Food_Delivery.Transformer;

import com.Nanda.Food_Delivery.Model.Restaurant;
import com.Nanda.Food_Delivery.Model.RestaurantAdmin;
import com.Nanda.Food_Delivery.dtoRequests.RestaurantRequest;
import com.Nanda.Food_Delivery.dtoResponse.RestaurantResponse;

public class RestaurantTransformer
{
	public static Restaurant requestToEntity(RestaurantRequest request)
    {
         return Restaurant.builder()
                 .restaurantName(request.getRestaurantName())
                 .contactNumber(request.getContactNumber())
                 .location(request.getLocation())
                 .restaurantCategory(request.getRestaurantCategory())
                 .imageURL(request.getImageURL())
                 .opens(request.getOpens())
                 .closes(request.getCloses())
                 .build();
    }
	
    public static Restaurant requestToEntity(RestaurantRequest request, RestaurantAdmin admin)
    {
         return Restaurant.builder()
                 .restaurantName(request.getRestaurantName())
                 .contactNumber(request.getContactNumber())
                 .location(request.getLocation())
                 .restaurantCategory(request.getRestaurantCategory())
                 .imageURL(request.getImageURL())
                 .opens(request.getOpens())
                 .closes(request.getCloses())
                 .admin(admin)
                 .build();
    }

    public static RestaurantResponse entityToResponse(Restaurant entity)
    {
        return RestaurantResponse.builder()
        		.id(entity.getId())
                .restaurantName(entity.getRestaurantName())
                .contactNumber(entity.getContactNumber())
                .location(entity.getLocation())
                .restaurantCategory(entity.getRestaurantCategory())
                .opened(entity.getOpened())
                .opens(entity.getOpens())
                .closes(entity.getCloses())
                .imageURL(entity.getImageURL())
                .build();
    }
    
    public static RestaurantResponse entityToResponseForAdmin(Restaurant entity)
    {
    	System.out.println(entity.getMenu()+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
        return RestaurantResponse.builder()
        		.id(entity.getId())
                .restaurantName(entity.getRestaurantName())
                .contactNumber(entity.getContactNumber())
                .location(entity.getLocation())
                .restaurantCategory(entity.getRestaurantCategory())
                .opened(entity.getOpened())
                .imageURL(entity.getImageURL())
                .opens(entity.getOpens())
                .closes(entity.getCloses())
                .adminUserName(entity.getAdmin().getUserName())
                .adminPassword(entity.getAdmin().getPassword())
                .menuResponse(MenuTransformer.entityToResponse(entity.getMenu()))
                .build();
    }
}
