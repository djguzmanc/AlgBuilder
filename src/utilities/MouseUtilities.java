package utilities;

import main.AlgBuilder;

public class MouseUtilities 
{
	public AlgBuilder applet;
	
	public MouseUtilities( AlgBuilder applet )
	{
		this.applet = applet;
	}
	
	public void mouseOverMainButtons( )
	{
		if ( applet.playButton.isMouseOver( ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			if ( applet.playButton.getBooleanValue( ) )
				applet.tagLabel.setText( "Pause algorithm." );
			else
				applet.tagLabel.setText( "Start algorithm." );
			
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 8, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( applet.tagLabel.getStringValue( ).length( ) ), 20 );
				applet.tagRect.setPosition( applet.mouseX + 8, applet.mouseY - 8 );
			}
		}
		else if ( applet.restart.isMouseOver( ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			applet.tagLabel.setText( "Restart algorithm." );
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 8, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( applet.tagLabel.getStringValue( ).length( ) ), 20 );
				applet.tagRect.setPosition( applet.mouseX + 8, applet.mouseY - 8 );
			}
		}
		else if ( applet.stepButton.isMouseOver( ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			applet.tagLabel.setText( "Execute next line." );
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 8, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( applet.tagLabel.getStringValue( ).length( ) ), 20 );
				applet.tagRect.setPosition( applet.mouseX + 8, applet.mouseY - 8 );
			}
		}
		else if ( applet.jumpButton.isMouseOver( ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			applet.tagLabel.setText( "Jump to next breakpoint." );
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 8, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( applet.tagLabel.getStringValue( ).length( ) ), 20 );
				applet.tagRect.setPosition( applet.mouseX + 8, applet.mouseY - 8 );
			}
		}
		else if ( applet.selectFile.isMouseOver( ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			applet.tagLabel.setText( "Select file (.algb extension)." );
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 8, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( applet.tagLabel.getStringValue( ).length( ) ), 20 );
				applet.tagRect.setPosition( applet.mouseX + 8, applet.mouseY - 8 );
			}
		}
		else if ( applet.slider.isMouseOver( ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			applet.tagLabel.setText( "Algorithm delay per\nline (milliseconds)." );
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 10, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( 20 ), 30 );
				applet.tagRect.setPosition( applet.mouseX + 10, applet.mouseY - 8 );
			}
		}
		else if ( applet.code.mouseInNumbers( applet.mouseX, applet.mouseY ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			applet.tagLabel.setText( "Add a breakpoint to the next executable line." );
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 8, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( applet.tagLabel.getStringValue( ).length( ) ), 20 );
				applet.tagRect.setPosition( applet.mouseX + 8, applet.mouseY - 8 );
			}
		}
		else if ( applet.openFile.isMouseOver( ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			applet.tagLabel.setText( "Open current file in editor." );
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 8, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( applet.tagLabel.getStringValue( ).length( ) ), 20 );
				applet.tagRect.setPosition( applet.mouseX + 8, applet.mouseY - 8 );
			}
		}
		else if ( applet.reloadFile.isMouseOver( ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			applet.tagLabel.setText( "Update changes in file." );
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 8, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( applet.tagLabel.getStringValue( ).length( ) ), 20 );
				applet.tagRect.setPosition( applet.mouseX + 8, applet.mouseY - 8 );
			}
		}
		else if ( applet.variableList.dropList.isMouseOver( ) )
		{
			if ( !applet.timer.isRunning( ) )
				applet.timer.start( );
			
			applet.tagLabel.setText( "Add a variable viewer." );
			if ( applet.passedSeconds > 0 )
			{
				applet.tagLabel.setVisible( true );
				applet.tagLabel.setPosition( applet.mouseX + 8, applet.mouseY - 4 );
				
				applet.tagRect.setVisible( true );
				applet.tagRect.setSize( applet.tagSizeMachete( applet.tagLabel.getStringValue( ).length( ) ), 20 );
				applet.tagRect.setPosition( applet.mouseX + 8, applet.mouseY - 8 );
			}
		}
		else
		{
			applet.timer.stop( );
			applet.tagLabel.setVisible( false );
			applet.tagRect.setVisible( false );
			applet.passedSeconds = 0;
		}	
	}

}
