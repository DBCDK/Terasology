/*
 * Copyright 2012 Benjamin Glatzel <benjamin.glatzel@me.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dk.dbc.commands;

import org.terasology.logic.commands.*;
import org.terasology.logic.manager.MessageManager;

/**
 * Testing commands
 *
 * @author hrmoller
 */
public class Commands implements CommandProvider {
    @Command(shortDescription = "bibcraft", helpText = "Writes bibcraft to the console")
    public void hello(){
        System.out.println("hest");
        MessageManager.getInstance().addMessage("bibcraft");
    }
}
