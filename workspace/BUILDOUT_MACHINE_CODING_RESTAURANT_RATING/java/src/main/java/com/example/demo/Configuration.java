package com.example.demo;

import com.example.demo.commands.AddRatingCommand;
import com.example.demo.commands.AddReviewCommand;
import com.example.demo.commands.AddUserCommand;
import com.example.demo.commands.CommandKeyword;
import com.example.demo.commands.CommandRegistry;
import com.example.demo.commands.CreateGreetingCommand;
import com.example.demo.commands.CreateRestaurantCommand;
import com.example.demo.commands.DescribeRestaurantCommand;
import com.example.demo.commands.GetGreetingCommand;
import com.example.demo.commands.GetReviewCommand;
import com.example.demo.commands.GetReviewFilterOrderCommand;
import com.example.demo.commands.ListGreetingCommand;
import com.example.demo.commands.ListRestaurantCommand;
import com.example.demo.repositories.GreetingRepository;
import com.example.demo.repositories.IGreetingRepository;
import com.example.demo.repositories.IRestaurantRepository;
import com.example.demo.repositories.IReviewRepository;
import com.example.demo.repositories.IUserRepository;
import com.example.demo.repositories.RestaurantRepository;
import com.example.demo.repositories.ReviewRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.GreetingService;
import com.example.demo.services.IRestaurantService;
import com.example.demo.services.IUserService;
import com.example.demo.services.RestaurantService;
import com.example.demo.services.UserService;

public class Configuration {
            // Singleton Pattern
            //create an object of Single Configuration Object
            private static Configuration instance = new Configuration();

            //make the constructor private so that this class cannot be
            //instantiated
            private Configuration(){}

            //Get the only object available
            public static Configuration getInstance(){
                return instance;
            }


            IUserRepository userRepository = new UserRepository();
            IUserService userService = new UserService(userRepository);

            IRestaurantRepository restaurantRepository = new RestaurantRepository();
            IReviewRepository reviewRepository = new ReviewRepository();
            IRestaurantService restaurantService = new RestaurantService(restaurantRepository);
            IRestaurantService restaurantServiceForRating = new RestaurantService(reviewRepository);
            IRestaurantService restaurantServices = new RestaurantService(restaurantRepository,reviewRepository);
           
            // Commands


            private final AddUserCommand addUserCommand = new AddUserCommand(userService);
            private final CreateRestaurantCommand registerRestaurantCommand = new CreateRestaurantCommand(restaurantService);

            private final AddRatingCommand addRatingCommand = new AddRatingCommand(restaurantServiceForRating);
            private final AddReviewCommand addReviewCommand = new AddReviewCommand(restaurantServiceForRating);

            private final GetReviewCommand getReviewCommand = new GetReviewCommand(restaurantServices);
            private final GetReviewFilterOrderCommand getReviewFilterCommand = new GetReviewFilterOrderCommand(restaurantServices);
            private final DescribeRestaurantCommand describeRestaurantCommand = new DescribeRestaurantCommand(restaurantServices);           

            private final ListRestaurantCommand listRestaurantCommand = new ListRestaurantCommand(restaurantServices);
            // Initialize commandRegistery
            private final CommandRegistry commandRegistry = new CommandRegistry();

            // Register commands 
            private void registerCommands(){
                commandRegistry.registerCommand(CommandKeyword.REGISTER_USER_COMMAND.getName(),addUserCommand);
                commandRegistry.registerCommand(CommandKeyword.REGISTER_RESTAURANT_COMMAND.getName(), registerRestaurantCommand);
                commandRegistry.registerCommand(CommandKeyword.ADD_RATING_COMMAND.getName(), addRatingCommand);
                commandRegistry.registerCommand(CommandKeyword.ADD_REVIEW_COMMAND.getName(), addReviewCommand);
                commandRegistry.registerCommand(CommandKeyword.GET_REVIEWS_COMMAND.getName(), getReviewCommand);
                commandRegistry.registerCommand(CommandKeyword.GET_REVIEWS_FILTER_ORDER_COMMAND.getName(), getReviewFilterCommand);
                commandRegistry.registerCommand(CommandKeyword.DESCRIBE_RESTAURANT_COMMAND.getName(), describeRestaurantCommand);
                commandRegistry.registerCommand(CommandKeyword.LIST_RESTAURANTS_COMMAND.getName(), listRestaurantCommand);
            }
            
            public CommandRegistry getCommandRegistry(){
                registerCommands();
                return commandRegistry;
            }
}
