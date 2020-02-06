/*
 * Author: Lev Vanyan
 * Lev.Vanyan@forthreal.com
 */

package com.forthreal.javafxsqrt;

import org.javatuples.Pair;

import com.forthreal.javafxsqrt.classes.SQRT;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.event.EventHandler;
import javafx.event.Event;

public class App extends Application 
{
	Button submit;
	TextField input;
	TextField output;
	Text outputText;
	
    public static void main( String[] args )
    {
        Application.launch( args );
    }

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		
		/* the window */
		Group root = new Group();
		
		/* the logical scene */
		Scene scene = new Scene( root, 210, 100 );
		stage.setScene( scene );
		stage.setTitle("Compute SQRT");

		stage.setResizable( false );
		
		root.requestFocus();
		
		/* input value to be computed */
		input = new TextField(  );
		input.setLayoutX( 10 );
		input.setLayoutY( 10 );
		input.setPrefSize( 80, 20 );
		input.setPrefColumnCount( 5 );
		root.getChildren().add( input );
		
		/* the button Compute */
		submit = new Button("Compute SQRT");
		submit.setLayoutX( 110 );
		submit.setLayoutY( 10 );
		root.getChildren().add( submit );
		
		/* for the display of the result */
		output = new TextField( );
		output.setLayoutX( 10 );
		output.setLayoutY( 50 );
		output.setPrefWidth( 198 );
		output.setEditable( false );
		output.setText("Result: ");
		root.getChildren().add( output );

		/* for the display of errors and exceptions */
		outputText = new Text();
		outputText.setLayoutX( 10 );
		outputText.setLayoutY( 100 );		
		root.getChildren().add( outputText );
		
		registerEvents( submit );
		
		stage.show();
	}
	
	private void handleButton( Event event )
	{	
		double inputValue;
			
		outputText.setText("");

		try
		{
			inputValue = Double.parseDouble( input.getText() );
				
			Pair<Boolean, Double> result = SQRT.sqrtAdv( (float) inputValue );
				
			if( result.getValue0() == true )
			{
				output.setText("Result: " + result.getValue1() );
			}
		}
		catch(Exception exc)
		{
			outputText.setText("Exception: " + exc.getLocalizedMessage() );			
		}

	}
	
	/* capture mouse and keyboard presses on the Compute button */
	private void registerEvents( Button eventGroup )
	{
		eventGroup.addEventHandler
		   (
			KeyEvent.KEY_PRESSED,
			new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event)
				{
					handleButton( event );
				}
				
			}
		   );
		
		eventGroup.addEventHandler
		  (
			MouseEvent.MOUSE_CLICKED,
			new EventHandler<MouseEvent>()
			{
				@Override
				public void handle(MouseEvent event)
				{
					handleButton( event );
				}
			}
		  );
	}
}
