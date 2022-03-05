package com.github.coryrobertson.AutoUpdater;

import com.github.coryrobertson.Logger.LogLevels;
import com.github.coryrobertson.Logger.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
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
    private ImageView completionImage;

    @FXML
    private void urlInputFieldEntered(ActionEvent ae)
    {
        //don't really need any of this

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
        //don't really need any of this

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
        if(urlText.getText().length() > 0 && destinationText.getText().length() > 0)
        {
            Logger.log("did update");
            String[] args = {urlText.getText(), DEFAULT_FILENAME, destinationText.getText()};
            completionImage.setOpacity(0.0);

            Updater.main(args);
            completionImage.setOpacity(1.0);
        }
        else
        {
            Logger.log("didn't update");
        }
    }

}
