/*
 * Copyright 2013 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.bitcoin.params;

import com.google.bitcoin.core.NetworkParameters;
import com.google.bitcoin.core.Sha256Hash;
import com.google.bitcoin.core.Utils;

import static com.google.common.base.Preconditions.checkState;

/**
 * Parameters for the main production network on which people trade goods and services.
 */
public class MainNetParams extends NetworkParameters {
    public MainNetParams() {
        super();
        interval = INTERVAL;
        targetTimespan = TARGET_TIMESPAN;
        proofOfWorkLimit = Utils.decodeCompactBits(0x1d00ffffL);
        dumpedPrivateKeyHeader = 128;
        addressHeader = 55;
        p2shHeader = 5;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        // PBC
        port = 8430;
        // PBC
        packetMagic = 0xf50468d2;
        // PBC
        genesisBlock.setDifficultyTarget(0x1d00ffffL);
        // PBC
        genesisBlock.setTime(1396571816);
        // PBC
        genesisBlock.setNonce(305735);

        id = ID_MAINNET;

        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        // PBC
        checkState(genesisHash.equals("000005e51dad3a29ac47b03745a9f5af0d8d8f543d6bc119099c5f12cd11e444"), //000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        // PBC
        // Removed checkpoints

        // PBC
        dnsSeeds = new String[] {
                // DNS
                "node1.paybullion.com",
                "node2.paybullion.com",
                "node3.paybullion.com",
                "node4.paybullion.com",
                "node5.paybullion.com",
                // Backup IP
                "107.170.121.182",
                "162.243.200.150",
                "128.199.193.235",
                "188.226.204.234",
                "107.170.212.76"
        };
    }

    private static MainNetParams instance;
    public static synchronized MainNetParams get() {
        if (instance == null) {
            instance = new MainNetParams();
        }
        return instance;
    }

    public String getPaymentProtocolId() {
        return PAYMENT_PROTOCOL_ID_MAINNET;
    }

    @Override
    public boolean isPrivateKeyValid(String privateKey) {
        if(null==privateKey || 0==privateKey.trim().length()) {
            return false;
        }
        return (privateKey.startsWith("5") && privateKey.length()==51);
    }
}
