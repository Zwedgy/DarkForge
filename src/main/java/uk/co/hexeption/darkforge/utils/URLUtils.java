/*******************************************************************************
 *     DarkForge a Forge Hacked Client
 *     Copyright (C) 2017  Hexeption (Keir Davis)
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/

package uk.co.hexeption.darkforge.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Hexeption on 09/04/2017.
 */
public class URLUtils {

    public static ArrayList getWebsiteContents(URL url) throws Exception {

        ArrayList fileContents = new ArrayList();
        URLConnection openConnection = url.openConnection();
        openConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(openConnection.getInputStream()));

        String fileLine = "";
        while ((fileLine = fileReader.readLine()) != null) {
            if (!fileLine.equals("")) {
                fileContents.add(fileLine);
            }
        }

        fileReader.close();
        return fileContents;
    }

}
