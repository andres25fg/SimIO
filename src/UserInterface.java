import javax.swing.JOptionPane;

/**
 * Class UserInterface
 *
 * Contains the definition of the GUI used in the app
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class UserInterface extends javax.swing.JFrame {
    private boolean slowModeFlag; // Flag to know it the simulation will use a Slow Mode
    private int slowModeSeconds; // Number of seconds to use in Slow Mode
    private int numSims; // Number of simulations
    private int secondsSimulation; // Number of seconds of the simulation
    private int timeout; // Number of seconds that determines a connection's timeout
    private int k; // Number of simultaneous connections the system can process
    private int n; // Number of processes the Query Processor can handle
    private int p; // Number of processes available for the execution of transactions
    private int m; // Number of processes to execute queries
    private Simulation simulation; // Instance of a Simulation

    /**
     * Constructor
     */
    public UserInterface() {
        initComponents(); // Initiates the GUI components
        this.setParamPanelInvisible(); // Sets some components invisible
        this.simPanel.setVisible(false); // Sets the simulation panel invisible
    }

    /**
     * Initiates the GUI components
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainMenu = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        paramMenu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        simSeconds = new javax.swing.JTextField();
        slowMode = new javax.swing.JCheckBox();
        numConnections = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        queryProcessorServers = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        transactionsServers = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        queryExecutionServers = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        simSecondsSlowMode = new javax.swing.JTextField();
        slowModeLabel = new javax.swing.JLabel();
        nextSimButton = new javax.swing.JButton();
        numSimulations = new javax.swing.JTextField();
        timeoutSeconds = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        simPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        simText = new javax.swing.JTextArea();
        starSimButton = new javax.swing.JButton();
        returnButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        slowModeLabel_simPanel = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        secsSim_simPanel = new javax.swing.JTextField();
        k_simPanel = new javax.swing.JTextField();
        n_simPanel = new javax.swing.JTextField();
        p_simPanel = new javax.swing.JTextField();
        t_simPanel = new javax.swing.JTextField();
        m_simPanel = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        sims_simPanel = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        slowModeSeconds_simPanel = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        clientAdmServers_SimPanel = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        processAdmServers_SimPanel = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        queryProcServers_SimPanel = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        transactionsServers_SimPanel = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        queryExecServers_SimPanel = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        processAdmServers_lq = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        queryProcServers_lq = new javax.swing.JTextField();
        jLabel32 = new javax.swing.JLabel();
        transactionsServers_lq = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        queryExecServers_lq = new javax.swing.JTextField();
        jLabel34 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        rejectecConnections = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        servedConnections = new javax.swing.JTextField();
        jLabel36 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel37 = new javax.swing.JLabel();
        clientAdmConnectionsServed = new javax.swing.JTextField();
        jLabel38 = new javax.swing.JLabel();
        processAdmConnectionsServed = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        queryProcConnectionsServed = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        transactionsConnectionsServed = new javax.swing.JTextField();
        jLabel41 = new javax.swing.JLabel();
        queryExecConnectionsServed = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        clock = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        actualModule = new javax.swing.JTextField();
        jLabel44 = new javax.swing.JLabel();
        actualEvent = new javax.swing.JTextField();
        jLabel45 = new javax.swing.JLabel();
        simNumber = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto Simulación - Investigación de Operaciones");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setName("frame"); // NOI18N

        mainMenu.setBackground(new java.awt.Color(255, 255, 255));

        startButton.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        startButton.setText("Iniciar");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 28)); // NOI18N
        jLabel1.setText("Proyecto de Simulación CI-1453");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel3.setText("B34494 - Kevin Mora Alfaro");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 22)); // NOI18N
        jLabel11.setText("Simulación de un DMBS");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel12.setText("Grupo 10:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel13.setText("B12867 - Andrés González Caldas");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jLabel14.setText("B25965 - Felipe Rosabal");

        javax.swing.GroupLayout mainMenuLayout = new javax.swing.GroupLayout(mainMenu);
        mainMenu.setLayout(mainMenuLayout);
        mainMenuLayout.setHorizontalGroup(
                mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuLayout.createSequentialGroup()
                                .addGap(155, 155, 155)
                                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(763, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(158, 158, 158))
        );
        mainMenuLayout.setVerticalGroup(
                mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainMenuLayout.createSequentialGroup()
                                .addGap(148, 148, 148)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(89, 89, 89)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(21, 21, 21)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(77, 77, 77)
                                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(148, Short.MAX_VALUE))
        );

        paramMenu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Cantidad de máxima de segundos por simulación:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 25)); // NOI18N
        jLabel4.setText("Parámetros de la simulación");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Número de simulaciones:");

        simSeconds.setText("1000");

        slowMode.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        slowMode.setText("  Simulación en modo lento");
        slowMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slowModeActionPerformed(evt);
            }
        });

        numConnections.setText("15");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Número de conexiones concurrentes (k):");

        queryProcessorServers.setText("3");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Número de procesos del procesador de consultas (n):");

        transactionsServers.setText("2");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Número de procesos para ejecución de transacciones (p):");

        queryExecutionServers.setText("1");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Número de procesos para la ejecución de consultas (m):");

        simSecondsSlowMode.setText("0");

        slowModeLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        slowModeLabel.setText("Cantidad de segundos entre eventos (Modo lento) :");

        nextSimButton.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        nextSimButton.setText("Siguiente");
        nextSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextSimButtonActionPerformed(evt);
            }
        });

        numSimulations.setText("1");

        timeoutSeconds.setText("15");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Cantidad de segundos del timeout:");

        helpButton.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/question.png"))); // NOI18N
        helpButton.setText("Ayuda");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout paramMenuLayout = new javax.swing.GroupLayout(paramMenu);
        paramMenu.setLayout(paramMenuLayout);
        paramMenuLayout.setHorizontalGroup(
                paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(paramMenuLayout.createSequentialGroup()
                                .addGap(171, 171, 171)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(timeoutSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(88, 88, 88)
                                                .addComponent(nextSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36))
                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                                .addComponent(slowMode, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(80, 80, 80)
                                                                .addComponent(slowModeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(simSecondsSlowMode, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(61, 61, 61)
                                                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                        .addComponent(simSeconds, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(numSimulations)
                                                                                        .addComponent(numConnections, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                                .addGap(62, 62, 62)
                                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(queryExecutionServers, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(transactionsServers)
                                                                                .addComponent(queryProcessorServers, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                                .addContainerGap(133, Short.MAX_VALUE))))
        );
        paramMenuLayout.setVerticalGroup(
                paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(paramMenuLayout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nextSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(128, 128, 128)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(queryProcessorServers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(transactionsServers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(queryExecutionServers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(numSimulations, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(simSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(numConnections, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(timeoutSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(65, 65, 65)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(slowMode, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(slowModeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(simSecondsSlowMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(284, Short.MAX_VALUE))
        );

        simPanel.setBackground(new java.awt.Color(255, 255, 255));

        simText.setEditable(false);
        simText.setColumns(20);
        simText.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        simText.setRows(5);
        jScrollPane2.setViewportView(simText);

        starSimButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        starSimButton.setText("Iniciar");
        starSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                starSimButtonActionPerformed(evt);
            }
        });

        returnButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        returnButton.setText("Regresar");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel15.setText("Cantidad de máxima de segundos por simulación:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel16.setText("Parámetros de la simulación");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("Número de conexiones concurrentes (k):");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Número de procesos para ejecución de transacciones (p):");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Número de procesos del procesador de consultas (n):");

        slowModeLabel_simPanel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        slowModeLabel_simPanel.setText("Cantidad de segundos entre eventos (Modo lento) :");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel21.setText("Número de procesos para la ejecución de consultas (m):");

        secsSim_simPanel.setEditable(false);

        k_simPanel.setEditable(false);

        n_simPanel.setEditable(false);

        p_simPanel.setEditable(false);

        t_simPanel.setEditable(false);

        m_simPanel.setEditable(false);

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel22.setText("Número de simulaciones:");

        sims_simPanel.setEditable(false);

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel23.setText("Cantidad de segundos del timeout:");

        slowModeSeconds_simPanel.setEditable(false);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel20.setText("Estado de los servidores");

        clientAdmServers_SimPanel.setEditable(false);

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel24.setText("Módulo Administrador de Clientes");

        processAdmServers_SimPanel.setEditable(false);

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel25.setText("Módulo Administrador de Procesos");

        queryProcServers_SimPanel.setEditable(false);

        jLabel26.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel26.setText("Módulo Procesamiento de Consultas");

        transactionsServers_SimPanel.setEditable(false);

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel27.setText("Módulo Transacciones");

        queryExecServers_SimPanel.setEditable(false);

        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel28.setText("Módulo Ejecutador de Consultas");

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel29.setText("Estado de las colas");

        processAdmServers_lq.setEditable(false);
        processAdmServers_lq.setText("0");

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel31.setText("Módulo Administrador de Procesos");

        queryProcServers_lq.setEditable(false);
        queryProcServers_lq.setText("0");

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel32.setText("Módulo Procesamiento de Consultas");

        transactionsServers_lq.setEditable(false);
        transactionsServers_lq.setText("0");

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel33.setText("Módulo Transacciones");

        queryExecServers_lq.setEditable(false);
        queryExecServers_lq.setText("0");

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel34.setText("Módulo Ejecutador de Consultas");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        rejectecConnections.setEditable(false);
        rejectecConnections.setText("0");

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel35.setText("Conexiones rechazadas:");

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel30.setText("Estado del sistema");

        servedConnections.setEditable(false);
        servedConnections.setText("0");

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel36.setText("Conexiones atendidas:");

        jLabel37.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel37.setText("Conexiones servidas por servidor");

        clientAdmConnectionsServed.setEditable(false);
        clientAdmConnectionsServed.setText("0");

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel38.setText("Módulo Administrador de Clientes");

        processAdmConnectionsServed.setEditable(false);
        processAdmConnectionsServed.setText("0");

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel39.setText("Módulo Administrador de Procesos");

        queryProcConnectionsServed.setEditable(false);
        queryProcConnectionsServed.setText("0");

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel40.setText("Módulo Procesamiento de Consultas");

        transactionsConnectionsServed.setEditable(false);
        transactionsConnectionsServed.setText("0");

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel41.setText("Módulo Transacciones");

        queryExecConnectionsServed.setEditable(false);
        queryExecConnectionsServed.setText("0");

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel42.setText("Módulo Ejecutador de Consultas");

        clock.setEditable(false);
        clock.setText("0");

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel43.setText("Reloj:");

        actualModule.setEditable(false);

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel44.setText("Módulo actual: ");

        actualEvent.setEditable(false);

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel45.setText("Evento actual:");

        simNumber.setEditable(false);
        simNumber.setText("0");

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel46.setText("Simulación número:");

        javax.swing.GroupLayout simPanelLayout = new javax.swing.GroupLayout(simPanel);
        simPanel.setLayout(simPanelLayout);
        simPanelLayout.setHorizontalGroup(
                simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(simPanelLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(transactionsConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(processAdmServers_lq, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(transactionsServers_lq, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(queryProcServers_lq, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(queryExecServers_lq, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(47, 47, 47))
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(clientAdmServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(processAdmServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(queryProcServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(queryExecServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(47, 47, 47))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simPanelLayout.createSequentialGroup()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator2)
                                                        .addComponent(jSeparator1)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, simPanelLayout.createSequentialGroup()
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(18, 18, 18)
                                                                                .addComponent(transactionsServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(0, 0, Short.MAX_VALUE)))
                                                .addContainerGap())
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(clientAdmConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(processAdmConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(queryProcConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(queryExecConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(48, 48, 48))
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel16)
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(jLabel15)
                                                                                        .addComponent(jLabel17)
                                                                                        .addComponent(jLabel19)
                                                                                        .addComponent(jLabel18)
                                                                                        .addComponent(jLabel21)
                                                                                        .addComponent(jLabel23)
                                                                                        .addComponent(slowModeLabel_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(24, 24, 24)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(slowModeSeconds_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(secsSim_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(n_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(p_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(m_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(t_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(k_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel22)
                                                                                .addGap(225, 225, 225)
                                                                                .addComponent(sims_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                .addGap(18, 18, 18)
                                                                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addComponent(actualEvent))
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                                .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(rejectecConnections, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                                                .addGroup(simPanelLayout.createSequentialGroup()
                                                                                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, simPanelLayout.createSequentialGroup()
                                                                                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                        .addComponent(actualModule, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                                .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(simNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                                .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(servedConnections, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                                        .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap())))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simPanelLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(starSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))
        );
        simPanelLayout.setVerticalGroup(
                simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(simPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(starSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(sims_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(secsSim_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(k_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(8, 8, 8)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(n_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addComponent(p_simPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(m_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(5, 5, 5)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(t_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                                .addGap(6, 6, 6)
                                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                        .addComponent(slowModeLabel_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                        .addComponent(slowModeSeconds_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(13, 13, 13))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(rejectecConnections, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(servedConnections, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(simNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(clock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(actualModule, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(actualEvent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(58, 58, 58)))
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(3, 3, 3)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(clientAdmServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(queryProcServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(processAdmServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(queryExecServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(9, 9, 9)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(transactionsServers_SimPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(queryProcServers_lq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(queryExecServers_lq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(processAdmServers_lq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(9, 9, 9)
                                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(transactionsServers_lq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addGap(18, 18, 18)
                                                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(clientAdmConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(queryProcConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(processAdmConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(queryExecConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(transactionsConnectionsServed, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(28, Short.MAX_VALUE))
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addComponent(jScrollPane2)
                                                .addGap(81, 81, 81))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(paramMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(simPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(mainMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(paramMenu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(simPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startButtonActionPerformed
        this.mainMenu.setVisible(false);
        this.setParamPanelVisible();
    }//GEN-LAST:event_startButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        JOptionPane.showOptionDialog(null,"Digite los parámetros (valores numéricos)\nque desea utilizar en la simulación.", "Ayuda", JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Continuar"},"default");
    }//GEN-LAST:event_helpButtonActionPerformed

    private void nextSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextSimButtonActionPerformed
        if(slowModeFlag == false) {
            this.setSlowModeSeconds(0);
        } else {
            this.setSlowModeSeconds(this.parseInt(this.simSecondsSlowMode.getText()));
        }
        this.setNumSims(this.parseInt(this.numSimulations.getText()));
        this.setSecondsSimulation(this.parseInt(this.simSeconds.getText()));
        this.setK(this.parseInt(this.numConnections.getText()));
        this.setN(this.parseInt(this.queryProcessorServers.getText()));
        this.setP(this.parseInt(this.transactionsServers.getText()));
        this.setM(this.parseInt(this.queryExecutionServers.getText()));
        this.setTimeout(this.parseInt(this.timeoutSeconds.getText()));

        this.paramMenu.setVisible(false);
        this.setSimPanelVisible();
    }//GEN-LAST:event_nextSimButtonActionPerformed

    private void slowModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_slowModeActionPerformed
        if(slowModeFlag == true) {
            slowModeFlag = false;
            this.slowModeLabel.setVisible(false);
            this.simSecondsSlowMode.setVisible(false);
        } else {
            slowModeFlag = true;
            this.slowModeLabel.setVisible(true);
            this.simSecondsSlowMode.setVisible(true);
        }
    }//GEN-LAST:event_slowModeActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        this.simPanel.setVisible(false);
        this.paramMenu.setVisible(true);
        this.setParamPanelVisible();
        this.simText.setText("");
    }//GEN-LAST:event_returnButtonActionPerformed

    private void starSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_starSimButtonActionPerformed
        this.starSimButton.setEnabled(false);
        this.returnButton.setEnabled(false);
        simulation = new Simulation(numSims, secondsSimulation, slowModeFlag, slowModeSeconds, timeout, k, n, p, m, this);
    }//GEN-LAST:event_starSimButtonActionPerformed

    public void showTextinGUI(String text) {
        this.simText.setText(simText.getText() + "\n" + text);
        simText.setCaretPosition(simText.getDocument().getLength());
    }

    public void showComment() {
        JOptionPane.showOptionDialog(null,"Digite los parámetros (valores numéricos)\nque desea utilizar en la simulación.", "Ayuda", JOptionPane.INFORMATION_MESSAGE,JOptionPane.INFORMATION_MESSAGE, null, new String[]{"Continuar"},"default");
    }

    public void showServersInformation(int clientAdmnServers, int processAdmnServers, int queryProcessorServers, int queryExecServers, int transactionsServers) {
        this.clientAdmServers_SimPanel.setText(Integer.toString(clientAdmnServers));
        this.processAdmServers_SimPanel.setText(Integer.toString(processAdmnServers));
        this.queryProcServers_SimPanel.setText(Integer.toString(queryProcessorServers));
        this.queryExecServers_SimPanel.setText(Integer.toString(queryExecServers));
        this.transactionsServers_SimPanel.setText(Integer.toString(transactionsServers));
    }

    public void showLqInformation(int processAdmnServersLq, int queryProcessorServersLq, int queryExecServersLq, int transactionsServersLq) {
        this.processAdmServers_lq.setText(Integer.toString(processAdmnServersLq));
        this.queryExecServers_lq.setText(Integer.toString(queryProcessorServersLq));
        this.queryExecServers_lq.setText(Integer.toString(queryExecServersLq));
        this.transactionsServers_lq.setText(Integer.toString(transactionsServersLq));
    }

    public void showServedConnectionsInformation(int clientAdmnServedConnections, int processAdmnServedConnections, int queryProcessorServedConnections, int queryExecServedConnections, int transactionsServedConnections) {
        this.clientAdmConnectionsServed.setText(Integer.toString(clientAdmnServedConnections));
        this.processAdmConnectionsServed.setText(Integer.toString(processAdmnServedConnections));
        this.queryProcConnectionsServed.setText(Integer.toString(queryProcessorServedConnections));
        this.queryExecConnectionsServed.setText(Integer.toString(queryExecServedConnections));
        this.transactionsConnectionsServed.setText(Integer.toString(transactionsServedConnections));
    }

    public void showConnectionsInformation(int served, int rejected) {
        this.servedConnections.setText(Integer.toString(served));
        this.rejectecConnections.setText(Integer.toString(rejected));
    }

    public void showClock(double clock) {
        this.clock.setText(Double.toString(clock));
    }

    public void showActualModule(String actualModule) {
        this.actualModule.setText(actualModule);
    }

    public void showActualEvent(String actualEvent) {
        this.actualEvent.setText(actualEvent);
    }

    public void showSimulationNumber(int simNumber) {
        this.simNumber.setText(Integer.toString(simNumber));
    }

    public void resetAfterSimulation() {
        this.clientAdmServers_SimPanel.setText(k_simPanel.getText());
        this.queryProcServers_SimPanel.setText(n_simPanel.getText());
        this.transactionsServers_SimPanel.setText(p_simPanel.getText());
        this.queryExecServers_SimPanel.setText(m_simPanel.getText());
        this.processAdmServers_SimPanel.setText("1");
        this.clientAdmConnectionsServed.setText("0");
        this.processAdmConnectionsServed.setText("0");
        this.queryProcConnectionsServed.setText("0");
        this.queryExecConnectionsServed.setText("0");
        this.transactionsConnectionsServed.setText("0");
        this.servedConnections.setText("0");
        this.rejectecConnections.setText("0");
        this.processAdmServers_lq.setText("0");
        this.queryExecServers_lq.setText("0");
        this.queryExecServers_lq.setText("0");
        this.transactionsServers_lq.setText("0");
        this.clock.setText("0");
        this.actualModule.setText("");
        this.actualEvent.setText("");
        this.simNumber.setText("0");
    }

    public void activateReturnButton() {
        this.returnButton.setEnabled(true);
    }

    public int parseInt(String text) {
        int textInt = 0;
        try {
            textInt = Integer.parseInt(text);
        } catch(Exception e) {
            textInt = 0;
        }
        return textInt;
    }

    public void setSlowModeFlag(boolean slowModeFlag) {
        this.slowModeFlag = slowModeFlag;
    }

    public void setSlowModeSeconds(int slowModeSeconds) {
        this.slowModeSeconds = slowModeSeconds;
    }

    public void setNumSims(int numSims) {
        this.numSims = numSims;
    }

    public void setSecondsSimulation(int secondsSimulation) {
        this.secondsSimulation = secondsSimulation;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setP(int p) {
        this.p = p;
    }

    public void setM(int m) {
        this.m = m;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void setSimPanelVisible(){
        this.starSimButton.setEnabled(true);
        this.simPanel.setVisible(true);
        this.sims_simPanel.setText(Integer.toString(numSims));
        this.secsSim_simPanel.setText(Integer.toString(secondsSimulation));
        this.k_simPanel.setText(Integer.toString(k));
        this.n_simPanel.setText(Integer.toString(n));
        this.p_simPanel.setText(Integer.toString(p));
        this.m_simPanel.setText(Integer.toString(m));
        this.t_simPanel.setText(Integer.toString(timeout));
        this.slowModeSeconds_simPanel.setText(Integer.toString(slowModeSeconds));
        if(slowModeFlag) {
            this.slowModeSeconds_simPanel.setVisible(true);
            this.slowModeLabel_simPanel.setVisible(true);
        } else {
            this.slowModeSeconds_simPanel.setVisible(false);
            this.slowModeLabel_simPanel.setVisible(false);
        }
        this.clientAdmServers_SimPanel.setText(k_simPanel.getText());
        this.queryProcServers_SimPanel.setText(n_simPanel.getText());
        this.transactionsServers_SimPanel.setText(p_simPanel.getText());
        this.queryExecServers_SimPanel.setText(m_simPanel.getText());
        this.processAdmServers_SimPanel.setText("1");
        this.clientAdmConnectionsServed.setText("0");
        this.processAdmConnectionsServed.setText("0");
        this.queryProcConnectionsServed.setText("0");
        this.queryExecConnectionsServed.setText("0");
        this.transactionsConnectionsServed.setText("0");
        this.servedConnections.setText("0");
        this.rejectecConnections.setText("0");
        this.processAdmServers_lq.setText("0");
        this.queryExecServers_lq.setText("0");
        this.queryExecServers_lq.setText("0");
        this.transactionsServers_lq.setText("0");
        this.clock.setText("0");
        this.actualModule.setText("");
        this.actualEvent.setText("");
        this.simNumber.setText("0");
    }

    public void setParamPanelVisible(){
        this.nextSimButton.setVisible(true);
        this.helpButton.setVisible(true);
        this.numSimulations.setVisible(true);
        this.simSeconds.setVisible(true);
        this.queryProcessorServers.setVisible(true);
        this.transactionsServers.setVisible(true);
        this.queryExecutionServers.setVisible(true);
        this.numConnections.setVisible(true);
        this.simSecondsSlowMode.setVisible(false);
        if(slowModeFlag) {
            this.slowModeLabel.setVisible(true);
            this.simSecondsSlowMode.setVisible(true);
        } else {
            this.slowModeLabel.setVisible(false);
            this.simSecondsSlowMode.setVisible(false);
        }
    }

    public void setParamPanelInvisible(){
        this.helpButton.setVisible(false);
        this.nextSimButton.setVisible(false);
        this.numSimulations.setVisible(false);
        this.simSeconds.setVisible(false);
        this.queryProcessorServers.setVisible(false);
        this.transactionsServers.setVisible(false);
        this.queryExecutionServers.setVisible(false);
        this.numConnections.setVisible(false);
        this.simSecondsSlowMode.setVisible(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField actualEvent;
    private javax.swing.JTextField actualModule;
    private javax.swing.JTextField clientAdmConnectionsServed;
    private javax.swing.JTextField clientAdmServers_SimPanel;
    private javax.swing.JTextField clock;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTextField k_simPanel;
    private javax.swing.JTextField m_simPanel;
    private javax.swing.JPanel mainMenu;
    private javax.swing.JTextField n_simPanel;
    private javax.swing.JButton nextSimButton;
    private javax.swing.JTextField numConnections;
    private javax.swing.JTextField numSimulations;
    private javax.swing.JTextField p_simPanel;
    private javax.swing.JPanel paramMenu;
    private javax.swing.JTextField processAdmConnectionsServed;
    private javax.swing.JTextField processAdmServers_SimPanel;
    private javax.swing.JTextField processAdmServers_lq;
    private javax.swing.JTextField queryExecConnectionsServed;
    private javax.swing.JTextField queryExecServers_SimPanel;
    private javax.swing.JTextField queryExecServers_lq;
    private javax.swing.JTextField queryExecutionServers;
    private javax.swing.JTextField queryProcConnectionsServed;
    private javax.swing.JTextField queryProcServers_SimPanel;
    private javax.swing.JTextField queryProcServers_lq;
    private javax.swing.JTextField queryProcessorServers;
    private javax.swing.JTextField rejectecConnections;
    private javax.swing.JButton returnButton;
    private javax.swing.JTextField secsSim_simPanel;
    private javax.swing.JTextField servedConnections;
    private javax.swing.JTextField simNumber;
    private javax.swing.JPanel simPanel;
    private javax.swing.JTextField simSeconds;
    private javax.swing.JTextField simSecondsSlowMode;
    private javax.swing.JTextArea simText;
    private javax.swing.JTextField sims_simPanel;
    private javax.swing.JCheckBox slowMode;
    private javax.swing.JLabel slowModeLabel;
    private javax.swing.JLabel slowModeLabel_simPanel;
    private javax.swing.JTextField slowModeSeconds_simPanel;
    private javax.swing.JButton starSimButton;
    private javax.swing.JButton startButton;
    private javax.swing.JTextField t_simPanel;
    private javax.swing.JTextField timeoutSeconds;
    private javax.swing.JTextField transactionsConnectionsServed;
    private javax.swing.JTextField transactionsServers;
    private javax.swing.JTextField transactionsServers_SimPanel;
    private javax.swing.JTextField transactionsServers_lq;
    // End of variables declaration//GEN-END:variables
}
