import javax.swing.JOptionPane;

/**
 * Clase UserInterface
 *
 * Esta clase contiene la definición de la interfaz de usuario y sus métodos correspondientes
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class UserInterface extends javax.swing.JFrame {
    private boolean slowModeFlag; // Booleano para saber si la simulación se va a hacer en modo lento
    private int slowModeSeconds; // Segundos de la simulación para el modo lento
    private int numSims; // Número de veces que se va a realizar la simulación
    private int secondsSimulation; // Segundos para la simulación normal
    private int timeout; // Segundos que tiene una conexion para ser atendida
    private int k; // Cantidad de conexiones que el sistema maneja concurrentemente
    private int n; // Cantidad de procesos que el procesador de consultas puede manejar
    private int p; // Cantidad de procesos para la ejecución de transacciones
    private int m; // Cantidad de procesos para ejecutar consultas
    private Simulation simulation; // Objeto Simulation para comunicarse

    /**
     * Método constructor
     */
    public UserInterface() {
        initComponents();
        this.setParamPanelInvisible();
        this.simPanel.setVisible(false);
    }

    /**
     * Método que inicializa los componentes de la interfaz gráfica
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Proyecto Simulación - Investigación de Operaciones");
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(255, 255, 255));
        setName("frame"); // NOI18N

        mainMenu.setBackground(new java.awt.Color(255, 255, 255));

        startButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        startButton.setText("Iniciar");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Proyecto de Simulación CI-1453");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("B34494 - Kevin Mora Alfaro");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setText("Simulación de un DMBS");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Grupo 10:");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("B12867 - Andrés González Caldas");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("B25965 - Felipe Rosabal");

        javax.swing.GroupLayout mainMenuLayout = new javax.swing.GroupLayout(mainMenu);
        mainMenu.setLayout(mainMenuLayout);
        mainMenuLayout.setHorizontalGroup(
                mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(mainMenuLayout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(mainMenuLayout.createSequentialGroup()
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(mainMenuLayout.createSequentialGroup()
                                                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 606, Short.MAX_VALUE)
                                                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(31, 31, 31))
                                        .addGroup(mainMenuLayout.createSequentialGroup()
                                                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        mainMenuLayout.setVerticalGroup(
                mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainMenuLayout.createSequentialGroup()
                                .addContainerGap(114, Short.MAX_VALUE)
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
                                .addGroup(mainMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(23, 23, 23))
        );

        paramMenu.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("Cantidad de máxima de segundos por simulación:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Parámetros de la simulación");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel5.setText("Número de simulaciones:");

        simSeconds.setText("0");

        slowMode.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        slowMode.setText("  Simulación en modo lento");
        slowMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                slowModeActionPerformed(evt);
            }
        });

        numConnections.setText("0");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel6.setText("Número de conexiones concurrentes (k):");

        queryProcessorServers.setText("0");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel7.setText("Número de procesos del procesador de consultas (n):");

        transactionsServers.setText("0");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel8.setText("Número de procesos para ejecución de transacciones (p):");

        queryExecutionServers.setText("0");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel9.setText("Número de procesos para la ejecución de consultas (m):");

        simSecondsSlowMode.setText("0");

        slowModeLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        slowModeLabel.setText("Cantidad de segundos entre eventos (Modo lento) :");

        nextSimButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        nextSimButton.setText("Siguiente");
        nextSimButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextSimButtonActionPerformed(evt);
            }
        });

        numSimulations.setText("0");

        timeoutSeconds.setText("0");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel10.setText("Cantidad de segundos del timeout:");

        helpButton.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
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
                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                                .addComponent(slowMode, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(80, 80, 80)
                                                                .addComponent(slowModeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(queryExecutionServers, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(timeoutSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                                                .addComponent(simSeconds, javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(transactionsServers)
                                                                                .addComponent(numSimulations)
                                                                                .addComponent(numConnections)
                                                                                .addComponent(queryProcessorServers, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(simSecondsSlowMode, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(248, Short.MAX_VALUE))
                                        .addGroup(paramMenuLayout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(43, 43, 43)
                                                .addComponent(nextSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(36, 36, 36))))
        );
        paramMenuLayout.setVerticalGroup(
                paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(paramMenuLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nextSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(46, 46, 46)
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
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(timeoutSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addGroup(paramMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(slowMode)
                                        .addComponent(slowModeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(simSecondsSlowMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32))
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

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
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

        javax.swing.GroupLayout simPanelLayout = new javax.swing.GroupLayout(simPanel);
        simPanel.setLayout(simPanelLayout);
        simPanelLayout.setHorizontalGroup(
                simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(simPanelLayout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simPanelLayout.createSequentialGroup()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(starSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(30, 30, 30))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, simPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel15)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(secsSim_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20))
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel17)
                                                        .addComponent(jLabel18)
                                                        .addComponent(jLabel19)
                                                        .addComponent(jLabel21)
                                                        .addComponent(jLabel23)
                                                        .addComponent(slowModeLabel_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(k_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(n_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(p_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(t_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(m_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(slowModeSeconds_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(20, 20, 20))
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel22)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(sims_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(20, 20, 20))))
        );
        simPanelLayout.setVerticalGroup(
                simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(simPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(simPanelLayout.createSequentialGroup()
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(starSimButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(returnButton, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(36, 36, 36)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(sims_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(2, 2, 2)
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
                                                .addGroup(simPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(p_simPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(73, Short.MAX_VALUE))
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

    private void starSimButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_starSimButtonActionPerformed
        this.starSimButton.setEnabled(false);
        simulation = new Simulation(numSims, secondsSimulation, slowModeFlag, slowModeSeconds, timeout, k, n, p, m, this);
    }//GEN-LAST:event_starSimButtonActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        this.simPanel.setVisible(false);
        this.paramMenu.setVisible(true);
        this.setParamPanelVisible();
    }//GEN-LAST:event_returnButtonActionPerformed

    public void showTextinGUI(String text) {
        this.simText.setText(simText.getText() + "\n" + text);
        simText.setCaretPosition(simText.getDocument().getLength());
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
        this.slowModeLabel.setVisible(false);
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField k_simPanel;
    private javax.swing.JTextField m_simPanel;
    private javax.swing.JPanel mainMenu;
    private javax.swing.JTextField n_simPanel;
    private javax.swing.JButton nextSimButton;
    private javax.swing.JTextField numConnections;
    private javax.swing.JTextField numSimulations;
    private javax.swing.JTextField p_simPanel;
    private javax.swing.JPanel paramMenu;
    private javax.swing.JTextField queryExecutionServers;
    private javax.swing.JTextField queryProcessorServers;
    private javax.swing.JButton returnButton;
    private javax.swing.JTextField secsSim_simPanel;
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
    private javax.swing.JTextField transactionsServers;
    // End of variables declaration//GEN-END:variables
}
