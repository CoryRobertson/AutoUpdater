package com.github.coryrobertson.AutoUpdater;

import com.github.coryrobertson.Logger.LogLevels;
import com.github.coryrobertson.Logger.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class UpdaterController
{
    final String DEFAULT_FILENAME = "d.7z";
    boolean urlTextInputGiven = false;
    boolean destinationTextInputGiven = false;

    public String url;
    public String destination;

    @FXML
    private TextField urlText;

    @FXML
    private TextField destinationText;

    @FXML
    private Button updaterButton;

    @FXML
    private void urlInputFieldEntered(ActionEvent ae)
    {
        url = urlText.getText();
        if (url.length() > 0)
        {
            Logger.log(url, LogLevels.LOG);
            urlTextInputGiven = true;
        }
    }

    @FXML
    private void destinationInputFieldEntered(ActionEvent ae)
    {
        destination = destinationText.getText();
        if (destination.length() > 0)
        {
            Logger.log(destination, LogLevels.LOG);
            destinationTextInputGiven = true;
        }

    }

    @FXML
    private void runUpdater(ActionEvent ae)
    {
        if(!(urlTextInputGiven && destinationTextInputGiven))
        {
            Logger.log("didnt update");
            return;
        }

        Logger.log("did update");

        //String[] args = {url, DEFAULT_FILENAME, destination};
        //Updater.main(args);
    }

}
