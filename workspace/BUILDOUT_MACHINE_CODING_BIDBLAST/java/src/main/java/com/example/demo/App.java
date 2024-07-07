/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example.demo;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import com.example.demo.entities.Event;
import com.example.demo.entities.Greeting;
import com.example.demo.entities.Members;
import com.example.demo.entities.RegistrationResult;
import com.example.demo.repositories.BidsRepository;
import com.example.demo.repositories.EventRepository;
import com.example.demo.repositories.GreetingRepository;
import com.example.demo.repositories.IBidsRepository;
import com.example.demo.repositories.IEventRepository;
import com.example.demo.repositories.IGreetingRepository;
import com.example.demo.repositories.IMemberRepository;
import com.example.demo.repositories.MemberRegisterRepository;
import com.example.demo.repositories.MemberRepository;
import com.example.demo.services.BidService;
import com.example.demo.services.EventService;
import com.example.demo.services.GreetingService;
import com.example.demo.services.MemberRegisterService;
import com.example.demo.services.MemberService;

public class App {

    // Initialize repositories
    private final IGreetingRepository greetingRepository = new GreetingRepository();
    private final IMemberRepository memberRepository = new MemberRepository();
    private final MemberService memberService = new MemberService(memberRepository);
    private final IEventRepository eventRepository = new EventRepository();
    private final EventService eventService = new EventService(eventRepository);
    private final MemberRegisterRepository memberRegisterRepository =
            new MemberRegisterRepository();
    private final MemberRegisterService memberRegisterService =
            new MemberRegisterService(memberRegisterRepository, memberRepository, eventRepository);
    private final IBidsRepository bidsRepository = new BidsRepository();
    private final BidService bidService =
            new BidService(bidsRepository, memberRepository, eventRepository);

    // Initialize services
    private final GreetingService greetingService = new GreetingService(greetingRepository);

    public static void main(String[] args) {

        // Test your code by ading commands in sample_input/sample_input_one.txt
        // Run run.sh script using "bash run.sh" in your terminal.
        if (args.length == 1) {
            List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
            String inputFile = commandLineArgs.get(0).split("=")[1];
            try {
                List<String> file_commands = Files.readAllLines(Paths.get(inputFile));
                // Execute the commands
                new App().run(file_commands);
                // deleteExtraSpace();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return;
        }

        // OR
        // Test your code by ading commands in this list
        List<String> inplace_commands = new LinkedList<>() {
            {
                add("CREATE_GREETING,Hello World!");
                add("CREATE_GREETING,Bye World!");
                add("LIST_GREETING");
                add("GET_GREETING,1");
            }
        };

        new App().run(inplace_commands);

    }


    public void run(List<String> commands) {

        // System.out.println(commands);

        Iterator<String> it = commands.iterator();
        while (it.hasNext()) {
            String line = it.next();
            if (line.equals("") || line == null) {
                break;
            }
            List<String> tokens = Arrays.asList(line.split(","));

            try {
                // Execute Services
                switch (tokens.get(0)) {
                    // case "CREATE_GREETING":
                    // CREATE_GREETING(tokens);
                    // break;
                    // case "LIST_GREETING":
                    // LIST_GREETING(tokens);
                    // break;
                    // case "GET_GREETING":
                    // GET_GREETING(tokens);
                    // break;
                    case "ADD_MEMBER":
                        ADD_MEMBER(tokens);
                        break;
                    case "ADD_EVENT":
                        ADD_EVENT(tokens);
                        break;
                    case "REGISTER_MEMBER":
                        REGISTER_MEMBER(tokens);
                        break;
                    case "SUBMIT_BID":
                        SUBMIT_BID(tokens);
                        break;
                    case "DECLARE_WINNER":
                        DECLARE_WINNER(tokens);
                        break;
                    // Add More case statements below to support other commands

                    default:
                        throw new RuntimeException("INVALID_COMMAND");
                }
                // deleteExtraSpace();
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }


    // CREATE_GREETING
    private void CREATE_GREETING(List<String> tokens) {
        String message = tokens.get(1);
        Greeting createGreet = greetingService.create(message);
        System.out.println(createGreet);
    }

    // LIST_GREETING
    private void LIST_GREETING(List<String> tokens) {
        List<Greeting> glist = greetingService.getAllGreetings();
        System.out.println(glist);
    }

    // GET_GREETING
    private void GET_GREETING(List<String> tokens) {
        Long id = Long.parseLong(tokens.get(1));
        Greeting getGreet = greetingService.getGreeting(id);
        System.out.println(getGreet);
    }

    private void ADD_MEMBER(List<String> tokens) {
        Members member = memberService.AddMembers(tokens);

        System.out.println("MEMBER_ADDED " + member.getId());
    }

    // LIST_GREETING
    private void ADD_EVENT(List<String> tokens) {
        Event event = eventService.addEvent(tokens);
        System.out.println("EVENT_ADDED " + event.getId());
    }

    // GET_GREETING
    private void REGISTER_MEMBER(List<String> tokens) {

        try {
            RegistrationResult registrationResult = memberRegisterService.registerMember(tokens);
            System.out.println("MEMBER_REGISTERED " + registrationResult.getMemberName() + " "
                    + registrationResult.getEventName());

        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getMessage());
        }
        // System.out.println("MEMBER_REGISTERED "+registrationResult.getEventName()+
        // registrationResult.getEventName());
    }

    private void SUBMIT_BID(List<String> tokens) {
        try {
            bidService.submitBid(tokens);
            System.out.println("BIDS_SUBMITTED");

        } catch (Exception e) {
            // TODO: handle exception

            System.out.println(e.getMessage());
        }
    }

    private void DECLARE_WINNER(List<String> tokens) {


        
            String name = bidService.declareWinner(Long.parseLong(tokens.get(1)));
            System.out.print(name);
        
            // TODO: handle exception
          //  System.out.println(e.getMessage());
        

    }
}


