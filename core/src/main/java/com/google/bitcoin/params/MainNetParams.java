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
        addressHeader = 0;
        p2shHeader = 5;
        acceptableAddressCodes = new int[] { addressHeader, p2shHeader };
        // PMC
        port = 9336;
        packetMagic = 0x950364d1;
        // PMC genesis block: http://blockchain.premineco.in/block/1e11cadfc3d9c24fe28b330395f97428af97199c404bac35959fdd7ff7588adf
        genesisBlock.setDifficultyTarget(0x1d00ffffL);
        // PMC
        genesisBlock.setTime(1390363105);
        // PMC
        genesisBlock.setNonce(2083236893);

        id = ID_MAINNET;

        spendableCoinbaseDepth = 100;
        String genesisHash = genesisBlock.getHashAsString();
        // PMC
        checkState(genesisHash.equals("1e11cadfc3d9c24fe28b330395f97428af97199c404bac35959fdd7ff7588adf"), //000000000019d6689c085ae165831e934ff763ae46a2a6c172b3f1b60a8ce26f
                genesisHash);

        // This contains (at a minimum) the blocks which are not BIP30 compliant. BIP30 changed how duplicate
        // transactions are handled. Duplicated transactions could occur in the case where a coinbase had the same
        // extraNonce and the same outputs but appeared at different heights, and greatly complicated re-org handling.
        // Having these here simplifies block connection logic considerably.
        // PMC
        // Removed checkpoints

        // PMC
        dnsSeeds = new String[] {
                "dnsseed.preminer.com",
                "65.167.153.125",
                "65.167.153.115",
                "54.84.78.238:59596",
                "67.255.7.120:56545",
                "207.68.220.150:4849",
                "74.192.137.146:36380",
                "24.9.85.199:48311",
                "5.250.177.28:35476",
                "95.108.78.144:20419",
                "95.85.26.168:49919",
                "85.174.56.220:57942",
                "84.180.104.115:56143",
                "5.28.95.38:57423",
                "186.89.31.209:54770",
                "208.94.43.227:49494",
                "150.140.159.248:63609",
                "66.228.41.85:33695",
                "54.197.167.39:57058",
                "88.70.209.203:59599",
                "58.177.139.8:56173",
                "124.149.21.43:52940",
                //"10.67.30.19" (Local network)
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
}
