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
                "seed.preminer.com", //redundant info
                "65.167.153.125", //pmc1
                "188.226.182.38", //node.preminer.com
                "65.167.153.115", //pmc2
                "faucet.premineco.in",
                "blockchain.premineco.in",
                // Extra nodes
                "24.249.152.169",
                "24.84.244.254",
                "207.68.214.214",
                "74.47.64.139",
                "95.108.78.144"
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
