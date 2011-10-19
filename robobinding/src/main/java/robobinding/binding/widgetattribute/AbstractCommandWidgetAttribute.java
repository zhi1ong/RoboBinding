/**
 * Copyright 2011 Cheng Wei, Robert Taylor
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */
package robobinding.binding.widgetattribute;

import robobinding.beans.Command;
import robobinding.beans.PresentationModelAdapter;
import robobinding.binding.CommandViewAttribute;
import android.content.Context;

/**
 * @since 1.0
 * @version $Revision: 1.0 $
 * @author Robert Taylor
 *
 */
public abstract class AbstractCommandWidgetAttribute implements CommandViewAttribute
{
	private String commandName;
	
	public AbstractCommandWidgetAttribute(String commandName)
	{
		this.commandName = commandName;
	}
	
	@Override
	public void bind(PresentationModelAdapter presentationModelAdapter, Context context)
	{
		Command command = findPreferredCommand(presentationModelAdapter);
		
		if(command == null)
			command = getNoArgsCommand(presentationModelAdapter);
			
		bind(command);
	}

	private Command getNoArgsCommand(PresentationModelAdapter presentationModelAdapter)
	{
		Command noArgsCommand = presentationModelAdapter.findCommand(commandName);
	
		if (noArgsCommand == null)
			throw new IllegalArgumentException("Cannot find command: " + commandName);
	
		return noArgsCommand;
	}

	private Command findPreferredCommand(PresentationModelAdapter presentationModelAdapter)
	{
		return presentationModelAdapter.findCommand(commandName, getPreferredCommandParameterTypes());
	}
	
	protected abstract void bind(Command command);
	
}
