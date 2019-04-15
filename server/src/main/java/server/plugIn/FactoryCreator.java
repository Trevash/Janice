package server.plugIn;

public class FactoryCreator {

    /**
     * This method is used to get the actual factory used to create the classes that are in the plugin
     *
     * @param plugInFactoryName the name of the class that implements the IDaoFactory interface
     *                          - it should probably be the same as the name of the jar file
     * @return the factory used to create the classes in the factory
     */
    public static IDaoFactory getFactory(String plugInFactoryName) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {
        String factoryClassName = "server.plugIn." + plugInFactoryName;
        return (IDaoFactory) Class.forName(factoryClassName).newInstance();


        // TODO implement so that it will actually load a DAO
        // code from my 240 server which might be useful
        //    // a static block: executes once, when a method/constructor for this class is initially called.
        //    static {
        //        try {
        //            final String driver = "org.sqlite.JDBC";
        //            Class.forName(driver);
        //        } catch (ClassNotFoundException ex) {
        //            ex.printStackTrace();
        //        }
        //    }
        //return null;
    }
}
