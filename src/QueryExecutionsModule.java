/**
 * Clase QueryExecutionsModule
 *
 * Felipe Rosabal
 * Kevin Mora Alfaro
 * Andrés González Caldas
 */
public class QueryExecutionsModule extends Module {

    public QueryExecutionsModule(int servers) {
        this.setFreeServers(servers);
    }

    //metodo que carga los datos de la conexion
    public void load(Connection c){

    }
    public double generateServiceTime(double diskblocks, String type){
       double time = Math.pow(diskblocks, 2) / 1000;
        if(type=="UPDATE"){
            time = 1;
        }else{
            if(type=="DDL"){
                time = 0.5;
            }
        }
        return time;
    }


}
