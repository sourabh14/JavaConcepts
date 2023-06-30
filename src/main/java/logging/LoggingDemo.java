package logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingDemo {

    // Creating a logger
    private static Logger LOG = LogManager.getLogger(LoggingDemo.class);

    public void execute() {
        System.out.println("\n\n\n--------- Logging - Log4J------------\n");

        /*
            Logging Framework:
                - The standard print statements help you to analyze the code flow and catch if there is any unexpected
                    result or break in the flow.
                - Advantages of logging framework- Quick debugging, problem diagnosis, easy maintenance
                - Drawbacks - Needs some extra code. Might increase app length.

            Need for logging framework - Why not use System.out.println for logging?
                - SOP only prints on console not on files. There's also no provision for keeping track of old logs.
                - SOP contains synchronized block to display, which is heavyweight, expensive and time consuming.
                - There's no difference bw logging levels- INFO, ERROR and WARNING

            Apache Log4J:
                - Apache Log4j is a Java-based logging framework. It has 2 versions – Log4j 1 and Log4j2.
                -

            Log4J features:
                - Allow more than one thread at a time, without introducing data inconsistency.
                - It won't slow down the app execution.
                - Allows multiple log levels

            Log4J Main Components:
                - Logger: It is responsible for getting logging msg from java application.
                - Appender: This object sends msg to destination. Appenders usually are only responsible for writing
                    the event data to the target destination. We may use multiple appenders.
                - Layout: Provides format of logging msg. It is used by appender.
                - Log Manager: It reads properties file
                - Filter: Takes decision whether msg must be logged or not
                - Object Render: This will use layout object to get string representation.


            Log Levels
                - Log levels are a mechanism to categorise logs. Levels used for identifying the severity of an event.
                - Log4j provides below levels:-
                        ALL – To log all events.
                        TRACE – A fine-grained debug message, typically capturing the flow through the application.
                        DEBUG – A general debugging event.
                        INFO – An event for informational purposes.
                        WARN – An event that might possibly lead to an error.
                        ERROR – An error in the application, possibly recoverable.
                        FATAL – A severe error that will prevent the application from continuing.
                        OFF – No events will be logged.

                - Log4j follows order as below:-
                    ALL < TRACE < DEBUG < INFO < WARN < ERROR < FATAL
                    - If we mention log level as INFO then all INFO, WARN, ERROR and FATAL events will be logged.
                    - If we mention log level as WARN then all WARN, ERROR and FATAL events will be logged. In simple
                        terms, all the levels below the specified level including the specified level will be considered.

            Appenders in Log4j 2:
                - ConsoleAppender – writes the data to System.out or System.err with the default begin the first one
                    (a Java best practice when logging in containers)
                - FileAppender – appender that uses the FileManager to write the data to a defined file
                - RollingFileAppender – appender that writes data to a defined file and rolls over the file according
                    to a defined policy


         */

        LOG.info("This is INFO log");
        LOG.warn("This is a WARNING log");
        LOG.error("This is a ERROR log");
        LOG.debug("This is a DEBUG log");

        /*
            Configuration:
                - A configuration file in Log4j2 helps us to set up where to log, how to log, and what to log. A
                    configuration file is used in Log4j2 to specify appenders and loggers mainly. This file is written
                    in XML, JSON, YAML, or properties format.

                - The default configuration, provided in the DefaultConfiguration class, will set up:
                    A ConsoleAppender attached to the root logger i.e. logs will be printed on the console.
                    A PatternLayout set to the pattern “%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} – %msg%n” attached to the ConsoleAppender
                - By default Log4j assigns the root logger to Level.ERROR and those logs will be printed on the standard
                    console. So since the default level is set to ERROR, both error and fatal will be logged.

                -


         */

    }
}
