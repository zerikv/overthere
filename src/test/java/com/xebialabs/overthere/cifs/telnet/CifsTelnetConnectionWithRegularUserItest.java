/*
 * Copyright (c) 2008-2013, XebiaLabs B.V., All rights reserved.
 *
 *
 * Overthere is licensed under the terms of the GPLv2
 * <http://www.gnu.org/licenses/old-licenses/gpl-2.0.html>, like most XebiaLabs Libraries.
 * There are special exceptions to the terms and conditions of the GPLv2 as it is applied to
 * this software, see the FLOSS License Exception
 * <http://github.com/xebialabs/overthere/blob/master/LICENSE>.
 *
 * This program is free software; you can redistribute it and/or modify it under the terms
 * of the GNU General Public License as published by the Free Software Foundation; version 2
 * of the License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this
 * program; if not, write to the Free Software Foundation, Inc., 51 Franklin St, Fifth
 * Floor, Boston, MA 02110-1301  USA
 */
package com.xebialabs.overthere.cifs.telnet;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;

import com.xebialabs.overthere.ConnectionOptions;
import com.xebialabs.overthere.OverthereConnectionItestBase;
import com.xebialabs.overthere.WindowsCloudHostListener;

import static com.xebialabs.overthere.ConnectionOptions.ADDRESS;
import static com.xebialabs.overthere.ConnectionOptions.JUMPSTATION;
import static com.xebialabs.overthere.ConnectionOptions.OPERATING_SYSTEM;
import static com.xebialabs.overthere.ConnectionOptions.PASSWORD;
import static com.xebialabs.overthere.ConnectionOptions.PORT;
import static com.xebialabs.overthere.ConnectionOptions.TEMPORARY_DIRECTORY_PATH;
import static com.xebialabs.overthere.ConnectionOptions.USERNAME;
import static com.xebialabs.overthere.OperatingSystemFamily.WINDOWS;
import static com.xebialabs.overthere.WindowsCloudHostListener.REGULAR_USER_ITEST_PASSWORD;
import static com.xebialabs.overthere.WindowsCloudHostListener.REGULAR_USER_ITEST_USERNAME;
import static com.xebialabs.overthere.WindowsCloudHostListener.getOptionsForTunnel;
import static com.xebialabs.overthere.cifs.CifsConnectionBuilder.CIFS_PORT;
import static com.xebialabs.overthere.cifs.CifsConnectionBuilder.CIFS_PROTOCOL;
import static com.xebialabs.overthere.cifs.CifsConnectionBuilder.CONNECTION_TYPE;
import static com.xebialabs.overthere.cifs.CifsConnectionBuilder.DEFAULT_CIFS_PORT;
import static com.xebialabs.overthere.cifs.CifsConnectionBuilder.DEFAULT_TELNET_PORT;
import static com.xebialabs.overthere.cifs.CifsConnectionBuilder.PATH_SHARE_MAPPINGS;
import static com.xebialabs.overthere.cifs.CifsConnectionType.TELNET;

@Test
@Listeners({WindowsCloudHostListener.class})
public class CifsTelnetConnectionWithRegularUserItest extends OverthereConnectionItestBase {

    @Override
    protected String getProtocol() {
        return CIFS_PROTOCOL;
    }

    @Override
    protected ConnectionOptions getOptions() {
        ConnectionOptions options = new ConnectionOptions();

        options.set(OPERATING_SYSTEM, WINDOWS);
        options.set(CONNECTION_TYPE, TELNET);

        options.set(ADDRESS, WindowsCloudHostListener.getHost().getHostName());
        options.set(PORT, DEFAULT_TELNET_PORT);
        options.set(CIFS_PORT, DEFAULT_CIFS_PORT);

        options.set(USERNAME, REGULAR_USER_ITEST_USERNAME);
        options.set(PASSWORD, REGULAR_USER_ITEST_PASSWORD);
        options.set(TEMPORARY_DIRECTORY_PATH, "C:\\overthere\\tmp");
        options.set(PATH_SHARE_MAPPINGS, ImmutableMap.of("C:\\overthere", "sharethere"));

        options.set(JUMPSTATION, getOptionsForTunnel());
        return options;
    }

    @Override
    protected String getExpectedConnectionClassName() {
        return CifsTelnetConnection.class.getName();
    }

}
