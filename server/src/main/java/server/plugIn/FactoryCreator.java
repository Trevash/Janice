package server.plugIn;

public class FactoryCreator {

    /**
     * This method is used to get the actual factory used to create the classes that are in the plugin
     *
     * @param plugIn the name of the plugIn that is being used
     * @return the factory used to create the classes in the factory
     */
    public static IDaoFactory getFactory(String plugIn) {

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
        return null;
    }
}
