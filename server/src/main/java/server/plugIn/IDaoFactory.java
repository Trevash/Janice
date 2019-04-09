package server.plugIn;

public interface IDaoFactory {

    //static IDaoFactory getInstance();

    IGameDao createGameDao();
    IUserDao createUserDao();

    // or alternatively
    //IPlugIn createPlugIn();

}
