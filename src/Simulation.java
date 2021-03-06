import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
/**
 * Class Simualation
 *
 * This class contains the definitions of the main Class in the system. This class handles the communication between all modules and the GUI
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class Simulation  {
    private double clock; // System's clock
    private boolean slowMode; // Flag to know if the simulation will use the Slow Mode
    private int slowModeSeconds; // Number of seconds to use in Slow Mode between events
    private int numSimulations; // Number of simulations
    private int secondsSimulation; // Number of seconds of the simulation
    private int timeOut=100; // Number of seconds that determines a connection's timeout
    private double lambda = 1/1.7143; // Arrival of clients: every 1.7143 seconds a new client arrives to the system (60/35)
    private ClientAdministratorModule clientAdministrator; // Client Administrator
    private ProcessAdministratorModule processAdministrator; // Process Administrator
    private QueryExecutionsModule queryExecutions; // Query Exections
    private QueryProcessorModule queryProcessor; // Query Processor
    private TransactionsModule transactions; // Transactions
    private PriorityQueue<QueryEvent> eventList; // Event's list of the system
    private StatisticsModule statistics = new StatisticsModule(); // Statistics
    private int numConections=0; // Number of connections in the system
    private int numConectionServed=0; // Number of connections served by the system
    private int numTimeOut=0; // Number of connections that end because of the timeout
    private int numRejected=0; // Number of connections rejected by the system


    //statistics for multiple simulations
    private int numConectionsMultiple=0; // Number of connections that
    private int numConectionServedMultiple=0; // Number of connections served by the system
    private int numTimeOutMultiple=0; // Number of connections that end because of the timeout
    private int numRejectedMultiple=0; // Number of connections rejected by the system

    private double AverageUpdateMultiple=0; // Average time of UPDATE queries
    private double AverageSelectMultiple=0; // Average time of SELECT queries
    private double AverageJoinMultiple=0; // Average time of JOIN queries
    private double AverageDDlMultiple=0; // Average time of DDL queries

    private int numUpdateMultiple=0; // Number of UPDATE queries
    private int numSelectMultiple=0; // Number of SELECT queries
    private int numJoinMultiple=0; // Number of JOIN queries
    private int numDDlMultiple=0;// Number of DDL queries
    private UserInterface userInterface; // GUI objet for communication

    //statistics by module

    //client administrator
    private double clientAdminAverageUpdateMultiple=0; // Average time of UPDATE queries
    private double clientAdminAverageSelectMultiple=0; // Average time of SELECT queries
    private double clientAdminAverageJoinMultiple=0; // Average time of JOIN queries
    private double clientAdminAverageDDlMultiple=0; // Average time of DDL queries
    private double clientAdminLqeMultiple=0;        //Average deque size
    private double clientAdminFreeModuleMultiple=0; // Free time the module spends

    //proces administrator
    private double procesAdminAverageUpdateMultiple=0; // Average time of UPDATE queries
    private double procesAdminAverageSelectMultiple=0; // Average time of SELECT queries
    private double procesAdminAverageJoinMultiple=0; // Average time of JOIN queries
    private double procesAdminAverageDDlMultiple=0; // Average time of DDL queries
    private double procesAdminLqeMultiple=0;        //Average deque size
    private double procesAdminFreeModuleMultiple=0; // Free time the module spends

    // query procesor
    private double queryProcesorAverageUpdateMultiple=0; // Average time of UPDATE queries
    private double queryProcesorAverageSelectMultiple=0; // Average time of SELECT queries
    private double queryProcesorAverageJoinMultiple=0; // Average time of JOIN queries
    private double queryProcesorAverageDDlMultiple=0; // Average time of DDL queries
    private double queryProcesorLqMultiple=0;        //Average deque size
    private double queryProcesorFreeModuleMultiple=0; // Free time the module spends

    //transactions
    private double transactionAverageUpdateMultiple=0; // Average time of UPDATE queries
    private double transactionAverageSelectMultiple=0; // Average time of SELECT queries
    private double transactionAverageJoinMultiple=0; // Average time of JOIN queries
    private double transactionAverageDDlMultiple=0; // Average time of DDL queries
    private double transactionLqMultiple=0;        //Average deque size
    private double transactionFreeModuleMultiple=0; // Free time the module spends

    //query executor
    private double queryExeAverageUpdateMultiple=0; // Average time of UPDATE queries
    private double queryExeAverageSelectMultiple=0; // Average time of SELECT queries
    private double queryExeAverageJoinMultiple=0; // Average time of JOIN queries
    private double queryExeAverageDDlMultiple=0; // Average time of DDL queries
    private double queryExeLqeMultiple=0;        //Average deque size
    private double queryExeFreeModuleMultiple=0; // Free time the module spends







    /**
     * Constructor
     *  @param numSims Number of simulations
     * @param secsSim Number of seconds of the simulation
     * @param slowMode Flag to know if the simulation will use the Slow Mode
     * @param slowModeSecs Number of seconds to use in Slow Mode between events
     * @param timeOut Number of seconds that determines a connection's timeout
     * @param k Number of simultaneous connections the system can process
     * @param n Number of processes the Query Processor can handle
     * @param p Number of processes available for the execution of transactions
     * @param m Number of processes to execute queries
     * @param gui
     */
    public Simulation(int numSims, int secsSim, boolean slowMode, int slowModeSecs, int timeOut, int k, int n, int p, int m, UserInterface gui){
        userInterface = gui; // The GUI sends itself so the Simulation can have its reference for communication
        this.setClock(0); // We initialize the clock on 0
        this.setSecondsSimulation(secsSim); // We set the number of seconds per simulation
        this.setNumSimulations(numSims); // We set the total number of simulations
        this.setSlowMode(slowMode); // Slow Mode's flag is set
        this.setSlowModeSeconds(slowModeSecs); // Slo Mode's seconds are set
        this.setTimeOut(timeOut); // We set the timeout

        Comparator<QueryEvent> comparator = new QueryComparator(); // The comparator that uses the priority queue is created
        PriorityQueue<QueryEvent> stack = new PriorityQueue<QueryEvent>(comparator); // The Priority Queue is initialized
        setStackQueries(stack);

        (new Thread()
        {
            public void run(){ // The main process is started on a different thread from the GUI, so we can do the simulation and refresh data on the GUI
                try {
                    beginSimulation(k, n, p, m); // The main method that processes the simulation is called
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    public void setStackQueries(PriorityQueue<QueryEvent> stackQueries) {
        this.eventList = stackQueries;
    }

    public PriorityQueue<QueryEvent> getStackQueries() {
        return this.eventList;
    }

    /**
     * Adds an event to the Event List (Priority Queue)
     * @param newEvent: event to be added in the event list
     */
    private void addQueryEvent(QueryEvent newEvent) {
        this.eventList.add(newEvent);
    }

    /**
     * Returns the Event List's size
     * @return
     */
    public int getPriorityQueueSize() {
        return eventList.size();
    }

    /**
     * Gets the next event int the and takes it out of the queue
     * @return QueryEvent: event at the head of the queue.
     */
    private QueryEvent getNextEvent() {
        return eventList.poll();
    }

    /**
     * Method that runs the simulation process
     * @param k: Number of simultaneous connections the system can process
     * @param n: Number of processes the Query Processor can handle
     * @param p: Number of processes available for the execution of transactions
     * @param m: Number of processes to execute queries
     * @throws Exception
     */
    public void beginSimulation(int k,int n, int p, int m) throws Exception {

        for(int i=0; i<numSimulations; i++) { // Cycle that run all the simulations specified by the parameters
            // For the arrivals, a null connection is added because it hasn't been created yet

            // All the modules needed by the Simulations are created with the servers specified in the parameters
            this.clientAdministrator = new ClientAdministratorModule(k);
            this.processAdministrator = new ProcessAdministratorModule(1);
            this.queryExecutions = new QueryExecutionsModule(m);
            this.queryProcessor = new QueryProcessorModule(n);
            this.transactions = new TransactionsModule(p);
            this.statistics = new StatisticsModule();

            generateHTMLindex(getNumSimulations());
            RandomGenerator random = new RandomGenerator(); // Random generator
            clock = 0; // The clock starts at 0
            eventList.clear(); // We clear the event list
            QueryEvent firstArrival = new QueryEvent(0,EventType.values()[0],null); // The first arrival is generated
            addQueryEvent(firstArrival); // We add the first arrival to the event list

            userInterface.showTextinGUI("Sumulación número: " + (i+1)); // GUI is refreshed
            numConections=0;
            numConectionServed=0;
            numTimeOut=0;
            numRejected=0;

            userInterface.showSimulationNumber(i+1);

            while(secondsSimulation > clock) { // While the clack is below the time limit per Simulation, the simulations processes new events
                procesEvent(); // An event is processed
                if(slowMode) { // Checks if the Slow Mode flag is activated
                    try {
                        TimeUnit.SECONDS.sleep(slowModeSeconds); // The thread is put to sleep by the seconds defined by the user for Slow Mode
                    } catch (InterruptedException e) {
                        //e.printStackTrace();
                    }
                }

                if(slowMode) { // The GUI refreshes the Clock data
                    userInterface.showTextinGUI("Reloj: " + round(clock));
                }
                userInterface.showClock(round(clock));

                QueryEvent nextArrival = new QueryEvent(random.poisson(lambda),EventType.values()[0],null); // We generate the next arrival with the poisson distribution
                addQueryEvent(nextArrival);
                // Server data is refreshed on the GUI: Servers status, queue information, clients served, connection information.
                userInterface.showServersInformation(clientAdministrator.getFreeServers(),processAdministrator.getFreeServers(),queryProcessor.getFreeServers(),queryExecutions.getFreeServers(),transactions.getFreeServers());
                userInterface.showLqInformation(processAdministrator.getQueueSize(), queryProcessor.getQueueSize(), queryExecutions.getQueueSize(), transactions.getPriorityQueueSize());
                userInterface.showServedConnectionsInformation(clientAdministrator.getNumClientsServed(), processAdministrator.getNumClientsServed(),queryProcessor.getNumClientsServed(),queryExecutions.getNumClientsServed(),transactions.getNumClientsServed());
                userInterface.showConnectionsInformation(this.getNumConectionServed(), this.getNumRejected());
            }
            // Every statistic is displayed at the end of the simulations inside the JTextArea
            userInterface.showTextinGUI("\n---- Estádisticas globales de la Simulación:\n");
            userInterface.showTextinGUI("Número de conexiones: "+ numConections);
            userInterface.showTextinGUI("Total de conexiones atendidas:" + numConectionServed);
            userInterface.showTextinGUI("Total de conexiones que hicieron timeout: "+ numTimeOut);
            userInterface.showTextinGUI("Total de conexiones rechazadas: "+ numRejected);

            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +statistics.getSelectAverageTime()+" num "+statistics.getNumSelect());
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "    +statistics.getDdlAverageTime()+" num "+statistics.getNumDdl());
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "   +statistics.getJoinAverageTime()+" num "+statistics.getNumJoin());
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: " +statistics.getUpdateAverageTime()+" num "+statistics.getNumUdpate());

            userInterface.showTextinGUI("\n---- Estádisticas de cada módulo:\n");
            userInterface.showTextinGUI("Módulo: Administración de Clientes");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +clientAdministrator.getStatistic().getSelectAverageTime()+" para "+clientAdministrator.getStatistic().getNumSelect() + " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: " +clientAdministrator.getStatistic().getDdlAverageTime()+" para "+clientAdministrator.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+clientAdministrator.getStatistic().getJoinAverageTime()+" para "+clientAdministrator.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+clientAdministrator.getStatistic().getUpdateAverageTime()+" para "+clientAdministrator.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+clientAdministrator.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+clientAdministrator.getStatistic().getAverageFreeTime(clientAdministrator.getMaxSimConnections()));
            userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+clientAdministrator.getStatistic().getMododuleFreeTime());

            userInterface.showTextinGUI("Lambda: " +clientAdministrator.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +clientAdministrator.getStatistic().getP(clientAdministrator.getMaxSimConnections()));
            userInterface.showTextinGUI("Ws: "+clientAdministrator.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+clientAdministrator.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +clientAdministrator.getStatistic().getU());
            userInterface.showTextinGUI("W: " +clientAdministrator.getStatistic().getW());
            userInterface.showTextinGUI("L: "+clientAdministrator.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+clientAdministrator.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+clientAdministrator.getStatistic().getLq());

            userInterface.showTextinGUI("\nMódulo: Administrador de procesos");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: "+processAdministrator.getStatistic().getSelectAverageTime()+" para "+processAdministrator.getStatistic().getNumSelect()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "+processAdministrator.getStatistic().getDdlAverageTime()+" para "+processAdministrator.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+processAdministrator.getStatistic().getJoinAverageTime()+" para "+processAdministrator.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+processAdministrator.getStatistic().getUpdateAverageTime()+" para "+processAdministrator.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+processAdministrator.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+processAdministrator.getStatistic().getAverageFreeTime(processAdministrator.getMaxSimConnections()));
            userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+processAdministrator.getStatistic().getMododuleFreeTime());

            userInterface.showTextinGUI("Lambda: " +processAdministrator.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +processAdministrator.getStatistic().getP(processAdministrator.getMaxSimConnections()));
            userInterface.showTextinGUI("Ws: "+processAdministrator.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+processAdministrator.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +processAdministrator.getStatistic().getU());
            userInterface.showTextinGUI("W: " +processAdministrator.getStatistic().getW());
            userInterface.showTextinGUI("L: "+processAdministrator.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+processAdministrator.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+processAdministrator.getStatistic().getLq());

            userInterface.showTextinGUI("\nMódulo: Ejecutor de sentencias");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: "+queryExecutions.getStatistic().getSelectAverageTime()+" para "+queryExecutions.getStatistic().getNumSelect()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "+queryExecutions.getStatistic().getDdlAverageTime()+" para "+queryExecutions.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+queryExecutions.getStatistic().getJoinAverageTime()+" para "+queryExecutions.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+queryExecutions.getStatistic().getUpdateAverageTime()+" para "+queryExecutions.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+queryExecutions.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+queryExecutions.getStatistic().getAverageFreeTime(queryExecutions.getMaxSimConnections()));
            userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+queryExecutions.getStatistic().getMododuleFreeTime());

            userInterface.showTextinGUI("Lambda: " +queryExecutions.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +queryExecutions.getStatistic().getP(queryExecutions.getMaxSimConnections()));
            userInterface.showTextinGUI("Ws: "+queryExecutions.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+queryExecutions.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +queryExecutions.getStatistic().getU());
            userInterface.showTextinGUI("W: " +queryExecutions.getStatistic().getW());
            userInterface.showTextinGUI("L: "+queryExecutions.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+queryExecutions.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+queryExecutions.getStatistic().getLq());

            userInterface.showTextinGUI("\nMódulo: Procesador de consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: "+queryProcessor.getStatistic().getSelectAverageTime() +" para "+queryProcessor.getStatistic().getNumSelect()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "+queryProcessor.getStatistic().getDdlAverageTime()+" para "+queryProcessor.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+queryProcessor.getStatistic().getJoinAverageTime()+" para "+queryProcessor.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+queryProcessor.getStatistic().getUpdateAverageTime()+" para "+queryProcessor.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+queryProcessor.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+queryProcessor.getStatistic().getAverageFreeTime(queryProcessor.getMaxSimConnections()));
            userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+queryProcessor.getStatistic().getMododuleFreeTime());

            userInterface.showTextinGUI("Lambda: " +queryProcessor.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +queryProcessor.getStatistic().getP(queryProcessor.getMaxSimConnections()));
            userInterface.showTextinGUI("Ws: "+queryProcessor.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+queryProcessor.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +queryProcessor.getStatistic().getU());
            userInterface.showTextinGUI("W: " +queryProcessor.getStatistic().getW());
            userInterface.showTextinGUI("L: "+queryProcessor.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+queryProcessor.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+queryProcessor.getStatistic().getLq());

            userInterface.showTextinGUI("\nMódulo: Transacciones");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: "+transactions.getStatistic().getSelectAverageTime()+" para "+transactions.getStatistic().getNumSelect()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "+transactions.getStatistic().getDdlAverageTime()+" para "+transactions.getStatistic().getNumDdl()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+transactions.getStatistic().getJoinAverageTime()+" para "+transactions.getStatistic().getNumJoin()+ " consultas");
            userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+transactions.getStatistic().getUpdateAverageTime()+" para "+transactions.getStatistic().getNumUdpate()+ " consultas");
            userInterface.showTextinGUI("Promedio de servidores libres: "+transactions.getStatistic().getAverageFreeServers());
            userInterface.showTextinGUI("Promedio de tiempo libre  por servidor: "+transactions.getStatistic().getAverageFreeTime(transactions.getMaxSimConnections()));
            userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+transactions.getStatistic().getMododuleFreeTime());

            userInterface.showTextinGUI("Lambda: " +transactions.getStatistic().getLambda());
            userInterface.showTextinGUI("p: " +transactions.getStatistic().getP(transactions.getMaxSimConnections()));
            userInterface.showTextinGUI("Ws: "+transactions.getStatistic().getWs());
            userInterface.showTextinGUI("Wq: "+transactions.getStatistic().getStackAverageTime());
            userInterface.showTextinGUI("U: " +transactions.getStatistic().getU());
            userInterface.showTextinGUI("W: " +transactions.getStatistic().getW());
            userInterface.showTextinGUI("L: "+transactions.getStatistic().getL());
            userInterface.showTextinGUI("Ls: "+transactions.getStatistic().getLs());
            userInterface.showTextinGUI("Lq: "+transactions.getStatistic().getLq());

            //Update the statistics of multiple simulations
            numConectionsMultiple +=numConections;
            numConectionServedMultiple+=numConectionServed;
            numTimeOutMultiple+=numTimeOut;
            numRejectedMultiple+=numRejected;

            AverageUpdateMultiple+=statistics.getUpdateAverageTime();
            AverageSelectMultiple+=statistics.getSelectAverageTime();
            AverageJoinMultiple+=statistics.getJoinAverageTime();
            AverageDDlMultiple+=statistics.getDdlAverageTime();
            numUpdateMultiple+=statistics.getNumUdpate();
            numSelectMultiple+=statistics.getNumSelect();
            numJoinMultiple+=statistics.getNumJoin();
            numDDlMultiple+=statistics.getNumDdl();

            clientAdminAverageUpdateMultiple+=clientAdministrator.getStatistic().getUpdateAverageTime();
            clientAdminAverageSelectMultiple+=clientAdministrator.getStatistic().getSelectAverageTime();
            clientAdminAverageJoinMultiple+=clientAdministrator.getStatistic().getJoinAverageTime();
            clientAdminAverageDDlMultiple+=clientAdministrator.getStatistic().getDdlAverageTime();
            clientAdminLqeMultiple += clientAdministrator.getStatistic().getLq();
            clientAdminFreeModuleMultiple += clientAdministrator.getStatistic().getMododuleFreeTime();

            procesAdminAverageUpdateMultiple+=processAdministrator.getStatistic().getUpdateAverageTime();
            procesAdminAverageSelectMultiple+=processAdministrator.getStatistic().getSelectAverageTime();
            procesAdminAverageJoinMultiple+=processAdministrator.getStatistic().getJoinAverageTime();
            procesAdminAverageDDlMultiple+=processAdministrator.getStatistic().getDdlAverageTime();
            procesAdminLqeMultiple += processAdministrator.getStatistic().getLq();
            procesAdminFreeModuleMultiple += processAdministrator.getStatistic().getMododuleFreeTime();

            queryProcesorAverageUpdateMultiple+=queryProcessor.getStatistic().getUpdateAverageTime();
            queryProcesorAverageSelectMultiple+=queryProcessor.getStatistic().getSelectAverageTime();
            queryProcesorAverageJoinMultiple+=queryProcessor.getStatistic().getJoinAverageTime();
            queryProcesorAverageDDlMultiple+=queryProcessor.getStatistic().getDdlAverageTime();
            queryProcesorLqMultiple += queryProcessor.getStatistic().getLq();
            queryProcesorFreeModuleMultiple += queryProcessor.getStatistic().getMododuleFreeTime();

            transactionAverageUpdateMultiple+=transactions.getStatistic().getUpdateAverageTime();
            transactionAverageSelectMultiple+=transactions.getStatistic().getSelectAverageTime();
            transactionAverageJoinMultiple+=transactions.getStatistic().getJoinAverageTime();
            transactionAverageDDlMultiple+=transactions.getStatistic().getDdlAverageTime();
            transactionLqMultiple += transactions.getStatistic().getLq();
            transactionFreeModuleMultiple += transactions.getStatistic().getMododuleFreeTime();

            queryExeAverageUpdateMultiple+=queryExecutions.getStatistic().getUpdateAverageTime();
            queryExeAverageSelectMultiple+=queryExecutions.getStatistic().getSelectAverageTime();
            queryExeAverageJoinMultiple+=queryExecutions.getStatistic().getJoinAverageTime();
            queryExeAverageDDlMultiple+=queryExecutions.getStatistic().getDdlAverageTime();
            queryExeLqeMultiple += queryExecutions.getStatistic().getLq();
            queryExeFreeModuleMultiple += queryExecutions.getStatistic().getMododuleFreeTime();


            // The HTML with the statistics is created
            generateHTML(i+1);
            if((i+1) != numSimulations) { // If the current simulation isn't the last one, the GUI resets some of the values
                userInterface.resetAfterSimulation();
            }

        }
        generateHTMLindex(getNumSimulations());
        //Print multiple simulations statistics

        AverageUpdateMultiple=round(AverageUpdateMultiple/numSimulations);
        AverageSelectMultiple=round(AverageSelectMultiple/numSimulations);
        AverageJoinMultiple=round(AverageJoinMultiple/numSimulations);
        AverageDDlMultiple=round(AverageDDlMultiple/numSimulations);

        clientAdminAverageUpdateMultiple=round(clientAdminAverageUpdateMultiple/numSimulations);
        clientAdminAverageSelectMultiple=round(clientAdminAverageSelectMultiple/numSimulations);
        clientAdminAverageJoinMultiple=round(clientAdminAverageJoinMultiple/numSimulations);
        clientAdminAverageDDlMultiple=round(clientAdminAverageDDlMultiple/numSimulations);
        clientAdminLqeMultiple =round(clientAdminLqeMultiple/numSimulations);

        procesAdminAverageUpdateMultiple=round(procesAdminAverageUpdateMultiple/numSimulations);
        procesAdminAverageSelectMultiple=round(procesAdminAverageSelectMultiple/numSimulations);
        procesAdminAverageJoinMultiple=round(procesAdminAverageJoinMultiple/numSimulations);
        procesAdminAverageDDlMultiple=round(procesAdminAverageDDlMultiple/numSimulations);
        procesAdminLqeMultiple =round(procesAdminLqeMultiple/numSimulations);

        queryProcesorAverageUpdateMultiple=round(queryProcesorAverageUpdateMultiple/numSimulations);
        queryProcesorAverageSelectMultiple=round(queryProcesorAverageSelectMultiple/numSimulations);
        queryProcesorAverageJoinMultiple=round(queryProcesorAverageJoinMultiple/numSimulations);
        queryProcesorAverageDDlMultiple=round(queryProcesorAverageDDlMultiple/numSimulations);
        queryProcesorLqMultiple=round(queryProcesorLqMultiple/numSimulations);

        transactionAverageUpdateMultiple=round(transactionAverageUpdateMultiple/numSimulations);
        transactionAverageSelectMultiple=round(transactionAverageSelectMultiple/numSimulations);
        transactionAverageJoinMultiple=round(transactionAverageJoinMultiple/numSimulations);
        transactionAverageDDlMultiple=round(transactionAverageDDlMultiple/numSimulations);
        transactionLqMultiple=round(transactionLqMultiple/numSimulations);

        queryExeAverageUpdateMultiple=round(queryExeAverageUpdateMultiple/numSimulations);
        queryExeAverageSelectMultiple=round(queryExeAverageSelectMultiple/numSimulations);
        queryExeAverageJoinMultiple=round(queryExeAverageJoinMultiple/numSimulations);
        queryExeAverageDDlMultiple=round(queryExeAverageDDlMultiple/numSimulations);
        queryExeLqeMultiple=round(queryExeLqeMultiple/numSimulations);

        userInterface.showTextinGUI("\n---- Estádisticas globales del Sistema:\n");
        userInterface.showTextinGUI("Número de conexiones: "+ numConectionsMultiple);
        userInterface.showTextinGUI("Total de conexiones atendidas:" + numConectionServedMultiple);
        userInterface.showTextinGUI("Total de conexiones que hicieron timeout: "+ numTimeOutMultiple);
        userInterface.showTextinGUI("Total de conexiones rechazadas: "+ numRejectedMultiple);

        userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +AverageSelectMultiple+" num "+numSelectMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: "    +AverageDDlMultiple+" num "+numDDlMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "   +AverageJoinMultiple+" num "+numJoinMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: " +AverageUpdateMultiple+" num "+numUpdateMultiple);

        userInterface.showTextinGUI("Módulo: Administración de Clientes");
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +clientAdminAverageSelectMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: " +clientAdminAverageDDlMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+clientAdminAverageJoinMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+clientAdminAverageUpdateMultiple);
        userInterface.showTextinGUI("lq: "+clientAdminLqeMultiple);
        userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+clientAdminFreeModuleMultiple);


        userInterface.showTextinGUI("\nMódulo: Administrador de procesos");
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " + procesAdminAverageSelectMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: " + procesAdminAverageDDlMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+ procesAdminAverageJoinMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+ procesAdminAverageUpdateMultiple);
        userInterface.showTextinGUI("lq: "+procesAdminLqeMultiple);
        userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+procesAdminFreeModuleMultiple);

        userInterface.showTextinGUI("\nMódulo: Ejecutor de sentencias");
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +queryExeAverageSelectMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: " +queryExeAverageDDlMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+queryExeAverageJoinMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+queryExeAverageUpdateMultiple);
        userInterface.showTextinGUI("lq: "+queryExeLqeMultiple);
        userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+queryExeFreeModuleMultiple);


        userInterface.showTextinGUI("\nMódulo: Procesador de consultas");
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +queryProcesorAverageSelectMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: " +queryProcesorAverageDDlMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+queryProcesorAverageJoinMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+queryProcesorAverageUpdateMultiple);
        userInterface.showTextinGUI("lq: "+queryProcesorLqMultiple/numSimulations);
        userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+queryProcesorFreeModuleMultiple);


        userInterface.showTextinGUI("\nMódulo: Transacciones");
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia SELECT: " +transactionAverageSelectMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia DDL: " +transactionAverageDDlMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia JOIN: "+transactionAverageJoinMultiple);
        userInterface.showTextinGUI("Promedio de tiempo de la sentencia UPDATE: "+transactionAverageUpdateMultiple);
        userInterface.showTextinGUI("lq: "+transactionLqMultiple);
        userInterface.showTextinGUI("Tiempo que el módulo paso ocioso: "+transactionFreeModuleMultiple);

        userInterface.activateReturnButton(); // The return button is activated again, so when the simulations are over, the user can return and change back parameters
        userInterface.showDialog("Las estadísticas se desplegarán en su buscador predeterminado automáticamente. \nTambién, el archivo con las estadísticas se encuentra dentro de la carpeta \"Statistics\"\nubicada en la misma carpeta de la aplicación.", "Simulación terminada");
        File htmlFile = new File("Statistics/index.html");
        Desktop.getDesktop().browse(htmlFile.toURI()); // The index file is open automatically on the default browser
    }

    /**
     * Checks if a Connection has reached the timeout
     */
    public boolean checkTimeOut(Connection c){
        boolean time_out = false;
        if(c.getArrivalTime()+timeOut<= clock){
            QueryEvent event = new QueryEvent(clock, EventType.values()[2], c);
            addQueryEvent(event);
            time_out = true;
        }
        return time_out;
    }

    /**
     * Processes and event
     */
    public void procesEvent(){
        QueryEvent actualEvent = this.getNextEvent(); // The next event from the queue is taken out

        clock =  actualEvent.getEventTime(); // The clock is changed to the event's start time
        // The method processes the event differently depending of the event's type

        if(slowMode) { // Event data in the GUI is refreshed
            userInterface.showTextinGUI("\nEvento actual: " + actualEvent.getType());
        }
        userInterface.showActualEvent(actualEvent.getType());

        switch (actualEvent.getType()) { // We get the type of event to know which case should be done
            case "CONNECTION_IN":
                numConections++; // The number of connections in the system is increased
                if (!clientAdministrator.checkMaxConnections()) { // We check if the Client Administrator has free servers or not
                    clientAdministrator.rejectConnection(); // If not, the connections is rejected
                    numRejected++; // number of rejected connections is increased

                } else { // If there are free servers, the connections is created then
                    Connection newConnection = clientAdministrator.createConnection(); // The Client Administrator creates a new connection
                    newConnection.setCurrentModule(ModuleFlag.values()[0]);
                    clientAdministrator.arrive(newConnection, clock); // The connection arrives to the Client Administrator module
                    newConnection.setType(); // Chooses randomly the type of query
                    newConnection.setArrivalTime(clock); // Sets the arrival time of the connection
                    double serviceTime = clientAdministrator.generateServiceTime();
                    clientAdministrator.updateStatistics(newConnection, serviceTime, clock);
                    //userInterface.showTextinGUI("\nllegada: " + (serviceTime+clock)); // The Process Administrator generates a service time
                    QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], newConnection);
                    eventList.add(event);
                }
                break;
            case "CONNECTION_OUT":
                numConectionServed++; // When a connection goes out of the system we increased the number of served connections
                Connection out = actualEvent.getConnection();
                updateStatistics(out.getType().toString(), out.getArrivalTime()); // We update the statistics when the connection is getting out
                clientAdministrator.exit(clock);
                break;
            case "TIME_OUT":
                clientAdministrator.exit(clock);
                Connection time_out = actualEvent.getConnection(); // If the connections does a timeout, it exits from the Client Administrator
                updateStatistics(time_out.getType().toString(), time_out.getArrivalTime());
                numTimeOut++; // We increased the number of timout connections
                break;
            case "EXIT_MODULE":
                // The connection exits a module and passes to the next one
                Connection actualConnection = actualEvent.getConnection();
                ModuleFlag actualModule = actualConnection.getCurrentModule(); // We find the actual module the connection is currently in

                if(slowMode) { // Actual module data is refreshed on the GUI
                    userInterface.showTextinGUI("\nMódulo actual: "  + actualModule);
                }
                userInterface.showActualModule("" + actualModule);
                // If the service time has passed, we create a timeout event
                boolean processing;
                // We locate the module the connection is in right now
                switch (actualModule.getModule()) {
                    case "CLIENT_ADMIN":
                        // We check if the connection has already passed through the transactions module

                        if (checkTimeOut(actualConnection) == false) {
                            //if (!actualConnection.getTransactionModule()) {
                                // The connections has not passed through the transactions module
                                processing = processAdministrator.arrive(actualConnection, clock); // The connection arrives to the next module
                                if (processing == true) {
                                    // If attended
                                    // The service time is calculated, we update the current module flag of the connection and the statistics are updated
                                    // Finally, the event is added to the event list
                                    double serviceTime = processAdministrator.generateServiceTime();
                                    actualConnection.setCurrentModule(ModuleFlag.values()[1]);
                                    processAdministrator.updateStatistics(actualConnection, serviceTime, clock);
                                    QueryEvent event = new QueryEvent((clock + serviceTime), EventType.values()[3], actualConnection);
                                    addQueryEvent(event);
                                }
                                // Else if the connections has already passed through the transactions module
                            //} else {
                                // We exit the connection from the module
                                // Create a CONNECTION_OUT type event
                                //QueryEvent event = new QueryEvent(clock, EventType.values()[1], actualConnection);
                                //addQueryEvent(event);
                           // }
                        }
                        break;

                    case "PROCESS_ADMIN":
                        // Because the connection is getting out the module. Exit is executed, which retunr a connection if the queue is not empty
                        Connection client_p = processAdministrator.exit(clock);
                        if (client_p != null) {
                            // The queue is not empty
                            // The service time is calculated, we update the current module flag of the connection and the statistics are updated
                            // Finally, the event is added to the event list
                            double serviceTime = processAdministrator.generateServiceTime();
                            client_p.setCurrentModule(ModuleFlag.values()[1]);
                            processAdministrator.updateStatistics(client_p, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_p);
                            addQueryEvent(event);
                        }
                        if (checkTimeOut(actualConnection) == false) {
                            processing = queryProcessor.arrive(actualConnection, clock); // The connection arrives to the next module
                            if (processing == true) {
                                // The queue is not empty
                                // The service time is calculated, we update the current module flag of the connection and the statistics are updated
                                // Finally, the event is added to the event list
                                double serviceTime = queryProcessor.generateServiceTime(actualConnection.getType().getReadOnly());
                                actualConnection.setCurrentModule(ModuleFlag.values()[2]);
                                queryProcessor.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], actualConnection);
                                addQueryEvent(event);
                            }
                        }
                        break;

                    case "QUERY_PROCESSOR":
                        // Because the connection is getting out the module. Exit is executed, which retunr a connection if the queue is not empty
                        Connection client_q_p = queryProcessor.exit(clock);
                        if (client_q_p != null) {
                            // The queue is not empty
                            // The service time is calculated, we update the current module flag of the connection and the statistics are updated
                            // Finally, the event is added to the event list
                            double serviceTime = queryProcessor.generateServiceTime(client_q_p.getType().getReadOnly());
                            client_q_p.setCurrentModule(ModuleFlag.values()[2]);
                            queryProcessor.updateStatistics(client_q_p, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_q_p);
                            addQueryEvent(event);
                        }
                        if (checkTimeOut(actualConnection) == false) {
                            processing = transactions.arrive(actualConnection, clock);// The connection arrives to the next module
                            if (processing == true) {
                                // The queue is not empty
                                // The service time is calculated, the number of blocks read are calculated, we update the current module flag of the connection and the statistics are updated
                                // Finally, the event is added to the event list
                                double diskBloks = transactions.loadDiskBloks(actualConnection.getType().toString());
                                actualConnection.setBlocksRead(diskBloks);
                                actualConnection.setCurrentModule(ModuleFlag.values()[3]);
                                double serviceTime = transactions.generateServiceTime(diskBloks);
                                transactions.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], actualConnection);
                                addQueryEvent(event);
                            }
                        }
                        break;

                    case "TRANSACTION":
                        //do { // This 'do while' block was intended to occupy the free servers after the DDL is finished executing
                            Connection client_t = transactions.exit(clock);
                            if (client_t != null) {
                                double diskBloks = transactions.loadDiskBloks(actualConnection.getType().toString());
                                client_t.setBlocksRead(diskBloks);
                                double serviceTime = transactions.generateServiceTime(diskBloks);
                                client_t.setCurrentModule(ModuleFlag.values()[3]);
                                transactions.updateStatistics(client_t, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_t);
                                addQueryEvent(event);
                            }
                       // }while (transactions.getFreeServers()<2 && transactions.getFreeServers()>0 && transactions.getServingDDL()==false && transactions.getPriorityQueueSize()>0 );

                        actualConnection.setTransactionModuleTrue();
                        if (checkTimeOut(actualConnection) == false) {
                            //The connection returns to the Client Administrator module
                            processing = queryExecutions.arrive(actualConnection, clock);
                            if (processing == true) {
                                double serviceTime = queryExecutions.generateServiceTime(actualConnection.getBlocksRead(), actualConnection.getType().toString());
                                actualConnection.setCurrentModule(ModuleFlag.values()[4]);
                                actualConnection.setBlocksRead(actualConnection.getDisckBlocks() / 3);
                                queryExecutions.updateStatistics(actualConnection, serviceTime, clock);
                                QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], actualConnection);
                                addQueryEvent(event);
                            }
                        }
                        break;

                    case "QUERY_EXE":

                        Connection client_q = queryExecutions.exit(clock);
                        if (client_q != null) {
                            double serviceTime = queryExecutions.generateServiceTime(client_q.getBlocksRead(), client_q.getType().toString());
                            client_q.setCurrentModule(ModuleFlag.values()[4]);
                            client_q.setBlocksRead(actualConnection.getDisckBlocks() / 3);
                            queryExecutions.updateStatistics(client_q, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[3], client_q);
                            addQueryEvent(event);
                        }
                        if (checkTimeOut(actualConnection) == false) {
                            //regresa al modulo de administracion de clientes
                            double serviceTime = actualConnection.getBlocksRead() / 2;
                            actualConnection.setCurrentModule(ModuleFlag.values()[0]);
                            clientAdministrator.updateStatistics(actualConnection, serviceTime, clock);
                            QueryEvent event = new QueryEvent(clock + serviceTime, EventType.values()[1], actualConnection);
                            addQueryEvent(event);
                        }
                        break;
                }
                break;
        }
    }

    /**
     * Updates the statistics of the type of query
     * @param type: type of query
     * @param arrival: time of arrival
     */
    public void updateStatistics(String type, double arrival){
        switch (type){
            case "UPDATE":
                statistics.setUpdateAverageTime(clock-arrival, false);
                break;
            case "SELECT":
                statistics.setSelectAverageTime(clock-arrival, false);
                break;
            case "DDL":
                statistics.setDdlAverageTime(clock-arrival, false);
                break;
            case "JOIN":
                statistics.setJoinAverageTime(clock-arrival, false);
        }
    }

    public void setClock(int clock) {
        this.clock = clock;
    }

    public void setSlowMode(boolean slowMode) {
        this.slowMode = slowMode;
    }

    public void setSlowModeSeconds(int slowModeSeconds) {
        this.slowModeSeconds = slowModeSeconds;
    }

    public void setNumSimulations(int numSimulations) {
        this.numSimulations = numSimulations;
    }

    public void setSecondsSimulation(int secondsSimulation) {
        this.secondsSimulation = secondsSimulation;
    }

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    /**
     * Rounds the number to three decimals
     * @param number
     * @return
     */
    public double round(double number){
        number = Math.round(number*1000);
        return number/1000;
    }


    public int getNumSimulations() {
        return numSimulations;
    }

    public int getNumConectionsMultiple() {
        return numConectionsMultiple;
    }

    public int getNumConectionServedMultiple() {
        return numConectionServedMultiple;
    }

    public int getNumTimeOutMultiple() {
        return numTimeOutMultiple;
    }

    public int getNumRejectedMultiple() {
        return numRejectedMultiple;
    }

    public double getAverageUpdateMultiple() {
        return AverageUpdateMultiple;
    }

    public double getAverageSelectMultiple() {
        return AverageSelectMultiple;
    }

    public double getAverageJoinMultiple() {
        return AverageJoinMultiple;
    }

    public double getAverageDDlMultiple() {
        return AverageDDlMultiple;
    }

    public int getNumUpdateMultiple() {
        return numUpdateMultiple;
    }

    public int getNumSelectMultiple() {
        return numSelectMultiple;
    }

    public int getNumJoinMultiple() {
        return numJoinMultiple;
    }

    public int getNumDDlMultiple() {
        return numDDlMultiple;
    }

    public double getClientAdminAverageUpdateMultiple() {
        return clientAdminAverageUpdateMultiple;
    }

    public double getClientAdminAverageSelectMultiple() {
        return clientAdminAverageSelectMultiple;
    }

    public double getClientAdminAverageJoinMultiple() {
        return clientAdminAverageJoinMultiple;
    }

    public double getClientAdminAverageDDlMultiple() {
        return clientAdminAverageDDlMultiple;
    }

    public double getClientAdminLqeMultiple() {
        return clientAdminLqeMultiple;
    }

    public double getClientAdminFreeModuleMultiple() {
        return clientAdminFreeModuleMultiple;
    }

    public double getProcesAdminAverageUpdateMultiple() {
        return procesAdminAverageUpdateMultiple;
    }

    public double getProcesAdminAverageSelectMultiple() {
        return procesAdminAverageSelectMultiple;
    }

    public double getProcesAdminAverageJoinMultiple() {
        return procesAdminAverageJoinMultiple;
    }

    public double getProcesAdminAverageDDlMultiple() {
        return procesAdminAverageDDlMultiple;
    }

    public double getProcesAdminLqeMultiple() {
        return procesAdminLqeMultiple;
    }

    public double getProcesAdminFreeModuleMultiple() {
        return procesAdminFreeModuleMultiple;
    }

    public double getQueryProcesorAverageUpdateMultiple() {
        return queryProcesorAverageUpdateMultiple;
    }

    public double getQueryProcesorAverageSelectMultiple() {
        return queryProcesorAverageSelectMultiple;
    }

    public double getQueryProcesorAverageJoinMultiple() {
        return queryProcesorAverageJoinMultiple;
    }

    public double getQueryProcesorAverageDDlMultiple() {
        return queryProcesorAverageDDlMultiple;
    }

    public double getQueryProcesorLqMultiple() {
        return queryProcesorLqMultiple;
    }

    public double getQueryProcesorFreeModuleMultiple() {
        return queryProcesorFreeModuleMultiple;
    }

    public double getTransactionAverageUpdateMultiple() {
        return transactionAverageUpdateMultiple;
    }

    public double getTransactionAverageSelectMultiple() {
        return transactionAverageSelectMultiple;
    }

    public double getTransactionAverageJoinMultiple() {
        return transactionAverageJoinMultiple;
    }

    public double getTransactionAverageDDlMultiple() {
        return transactionAverageDDlMultiple;
    }

    public double getTransactionLqMultiple() {
        return transactionLqMultiple;
    }

    public double getTransactionFreeModuleMultiple() {
        return transactionFreeModuleMultiple;
    }

    public double getQueryExeAverageUpdateMultiple() {
        return queryExeAverageUpdateMultiple;
    }

    public double getQueryExeAverageSelectMultiple() {
        return queryExeAverageSelectMultiple;
    }

    public double getQueryExeAverageJoinMultiple() {
        return queryExeAverageJoinMultiple;
    }

    public double getQueryExeAverageDDlMultiple() {
        return queryExeAverageDDlMultiple;
    }

    public double getQueryExeLqeMultiple() {
        return queryExeLqeMultiple;
    }

    public double getQueryExeFreeModuleMultiple() {
        return queryExeFreeModuleMultiple;
    }

    /**
     * Generates the index file
     * @param simulations: number of simulations
     * @throws Exception
     */
    public void generateHTMLindex(int simulations) throws Exception {

        File dir = new File("Statistics");
        dir.mkdir();

        VelocityEngine ve = new VelocityEngine();
        ve.init();

        Template t = ve.getTemplate("Index.vm");
        Vector<String> v=new Vector<String>();

        for(int i=1; i<=getNumSimulations(); i++) {
            v.add("Simulaci&oacuten" + Integer.toString(i));
        }

        VelocityContext vc = new VelocityContext();
        vc.put("List", v);
        //vc.put("name", "Simulaci&oacuten"+Integer.toString(currentSim));
        vc.put("time", simulations);
        vc.put("simtime", getSecondsSimulation());
        vc.put("slowmode", getSlowModeSeconds());
        vc.put("k", clientAdministrator.getMaxSimConnections());
        vc.put("n", queryProcessor.getMaxSimConnections());
        vc.put("p", transactions.getMaxSimConnections());
        vc.put("m", queryExecutions.getMaxSimConnections());

        vc.put("connections", getNumConectionsMultiple());
        vc.put("served", getNumConectionServedMultiple());
        vc.put("timeout", getNumTimeOutMultiple());
        vc.put("rejected", getNumRejectedMultiple());
        //vc.put("w",);

        //average Lifetime per module
        vc.put("lqc", getClientAdminLqeMultiple());
        vc.put("lqqp", getQueryProcesorLqMultiple());
        vc.put("lqt", getTransactionLqMultiple());
        vc.put("lqqe", getQueryExeLqeMultiple());
        vc.put("lqp", getProcesAdminLqeMultiple());

        vc.put("ddl", getAverageDDlMultiple());
        vc.put("join", getAverageJoinMultiple());
        vc.put("select", getAverageSelectMultiple());
        vc.put("update", getAverageUpdateMultiple());

        vc.put("lazy1",getClientAdminFreeModuleMultiple());
        vc.put("lazy2", getProcesAdminFreeModuleMultiple());
        vc.put("lazy3", getQueryExeFreeModuleMultiple());
        vc.put("lazy4", getQueryProcesorFreeModuleMultiple());
        vc.put("lazy5", getTransactionFreeModuleMultiple());

        StringWriter sw = new StringWriter();
        t.merge(vc, sw);

//        System.out.println(sw);
        File file = new File("Statistics/Index.html");
        FileWriter fw = new FileWriter(file);
        fw.write(sw.toString());
        fw.close();
    }


    /**
     * Generates the HTML with the statistics
     * @param currentSim: number of the simulation that just ended
     * @throws Exception
     */

    public void generateHTML(int currentSim) throws Exception {

        VelocityEngine ve = new VelocityEngine();
        ve.init();

        Template t = ve.getTemplate("simulation.vm");
        //Vector<String> v=new Vector<String>();

        VelocityContext vc = new VelocityContext();

        vc.put("name", "Simulaci&oacuten"+Integer.toString(currentSim));
        vc.put("simtime", getSecondsSimulation());
        vc.put("slowmode", getSlowModeSeconds());
        vc.put("k", clientAdministrator.getMaxSimConnections());
        vc.put("n", queryProcessor.getMaxSimConnections());
        vc.put("p", transactions.getMaxSimConnections());
        vc.put("m", queryExecutions.getMaxSimConnections());
        vc.put("t", getTimeOut());
        vc.put("connections", getNumConections());
        vc.put("served", getNumConectionServed());
        vc.put("timeout", getNumTimeOut());
        vc.put("rejected", getNumRejected());
        vc.put("w", statistics.getW());
        vc.put("lqc", clientAdministrator.getStatistic().getLq());
        vc.put("lqqp", queryProcessor.getStatistic().getLq());
        vc.put("lqt", transactions.getStatistic().getLq());
        vc.put("lqqe", queryExecutions.getStatistic().getLq());
        vc.put("lqp", processAdministrator.getStatistic().getLq());

        vc.put("lambda1", clientAdministrator.getStatistic().getLambda());
        vc.put("lambda2", processAdministrator.getStatistic().getLambda());
        vc.put("lambda3", queryExecutions.getStatistic().getLambda());
        vc.put("lambda4", queryProcessor.getStatistic().getLambda());
        vc.put("lambda5", transactions.getStatistic().getLambda());

        vc.put("mu1", clientAdministrator.getStatistic().getU());
        vc.put("mu2", processAdministrator.getStatistic().getU());
        vc.put("mu3", queryExecutions.getStatistic().getU());
        vc.put("mu4", queryProcessor.getStatistic().getU());
        vc.put("mu5", transactions.getStatistic().getU());

        vc.put("rho1", clientAdministrator.getStatistic().getP(clientAdministrator.getMaxSimConnections()));
        vc.put("rho2", processAdministrator.getStatistic().getP(processAdministrator.getMaxSimConnections()));
        vc.put("rho3", queryExecutions.getStatistic().getP(queryExecutions.getMaxSimConnections()));
        vc.put("rho4", queryProcessor.getStatistic().getP(queryProcessor.getMaxSimConnections()));
        vc.put("rho5", transactions.getStatistic().getP(transactions.getMaxSimConnections()));


        vc.put("L1", clientAdministrator.getStatistic().getL());
        vc.put("L2", processAdministrator.getStatistic().getL());
        vc.put("L3", queryExecutions.getStatistic().getL());
        vc.put("L4", queryProcessor.getStatistic().getL());
        vc.put("L5", transactions.getStatistic().getL());

        vc.put("Ls1", clientAdministrator.getStatistic().getLs());
        vc.put("Ls2", processAdministrator.getStatistic().getLs());
        vc.put("Ls3", queryExecutions.getStatistic().getLs());
        vc.put("Ls4", queryProcessor.getStatistic().getLs());
        vc.put("Ls5", transactions.getStatistic().getLs());

        vc.put("Lq1", clientAdministrator.getStatistic().getLq());
        vc.put("Lq2", processAdministrator.getStatistic().getLq());
        vc.put("Lq3", queryExecutions.getStatistic().getLq());
        vc.put("Lq4", queryProcessor.getStatistic().getLq());
        vc.put("Lq5", transactions.getStatistic().getLq());

        vc.put("W", statistics.getW());

        vc.put("W1", clientAdministrator.getStatistic().getW());
        vc.put("W2", processAdministrator.getStatistic().getW());
        vc.put("W3", queryExecutions.getStatistic().getW());
        vc.put("W4", queryProcessor.getStatistic().getW());
        vc.put("W5", transactions.getStatistic().getW());

        vc.put("Wq1", clientAdministrator.getStatistic().getStackAverageTime());
        vc.put("Wq2", processAdministrator.getStatistic().getStackAverageTime());
        vc.put("Wq3", queryExecutions.getStatistic().getStackAverageTime());
        vc.put("Wq4", queryProcessor.getStatistic().getStackAverageTime());
        vc.put("Wq5", transactions.getStatistic().getStackAverageTime());

        vc.put("Ws1", clientAdministrator.getStatistic().getWs());
        vc.put("Ws2", processAdministrator.getStatistic().getWs());
        vc.put("Ws3", queryExecutions.getStatistic().getWs());
        vc.put("Ws4", queryProcessor.getStatistic().getWs());
        vc.put("Ws5", transactions.getStatistic().getWs());

        vc.put("lazy1", clientAdministrator.getStatistic().getAverageFreeTime(clientAdministrator.getMaxSimConnections()));
        vc.put("lazy2", processAdministrator.getStatistic().getAverageFreeTime(processAdministrator.getMaxSimConnections()));
        vc.put("lazy3", queryExecutions.getStatistic().getAverageFreeTime(queryExecutions.getMaxSimConnections()));
        vc.put("lazy4", queryProcessor.getStatistic().getAverageFreeTime(queryProcessor.getMaxSimConnections()));
        vc.put("lazy5", transactions.getStatistic().getAverageFreeTime(transactions.getMaxSimConnections()));

        //  vc.put("active1", 1-clientAdministrator.getStatistic().getAverageFreeTime(clientAdministrator.getMaxSimConnections()));
        // vc.put("active2", 1-processAdministrator.getStatistic().getAverageFreeTime(processAdministrator.getMaxSimConnections()));
        // vc.put("active3", 1-queryExecutions.getStatistic().getAverageFreeTime(queryExecutions.getMaxSimConnections()));
        /// vc.put("active4", 1-queryProcessor.getStatistic().getAverageFreeTime(queryProcessor.getMaxSimConnections()));
        /// vc.put("active5", 1-transactions.getStatistic().getAverageFreeTime(transactions.getMaxSimConnections()));


        vc.put("ddl", statistics.getDdlAverageTime());
        vc.put("join", statistics.getJoinAverageTime());
        vc.put("select", statistics.getSelectAverageTime());
        vc.put("update", statistics.getUpdateAverageTime());

        StringWriter sw = new StringWriter();
        t.merge(vc, sw);

        //      System.out.println(sw);
        File file = new File("Statistics/Simulaci&oacuten"+Integer.toString(currentSim)+".html");
        FileWriter fw = new FileWriter(file);
        fw.write(sw.toString());
        fw.close();
    }

    public int getNumConections() {
        return numConections;
    }

    public int getNumConectionServed() {
        return numConectionServed;
    }

    public int getNumTimeOut() {
        return numTimeOut;
    }

    public int getNumRejected() {
        return numRejected;
    }

    public int getTimeOut() {
        return timeOut;
    }

    public int getSecondsSimulation() {
        return secondsSimulation;
    }

    public int getSlowModeSeconds() {
        return slowModeSeconds;
    }

    /**
     * Main
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Sets the GUI with a Nimbus look
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UserInterface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        // Creates the GUI form and displays it
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserInterface().setVisible(true);
            }
        });
    }



    /**
     * Nested class QueryComparator.
     * Has the definition of the comparator that will use the Event List, which is a priority queue
     */
    public class QueryComparator implements Comparator<QueryEvent> {
        /**
         * Method that makes the comparision between the two queries
         * @param query1
         * @param query2
         * @return
         */
        public int compare(QueryEvent query1, QueryEvent query2) {
            // Makes a subtraction of the queries times to determined which one goes first in que queue
            int priority;
            if(query1.getEventTime() > query2.getEventTime()){
                priority = 1;
            }else{
                priority = -1;
            }
            return priority;
        }
    }
}
