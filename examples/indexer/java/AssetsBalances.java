// AssetsBalances.java
// requires java-algorand-sdk 1.4.0 or higher (see pom.xml)
package com.algorand.javatest.indexer;

import com.algorand.algosdk.v2.client.common.IndexerClient;
import com.algorand.algosdk.v2.client.common.Client;
import com.algorand.algosdk.v2.client.common.Response;
import com.algorand.algosdk.v2.client.model.AssetBalancesResponse;
import org.json.JSONObject;

public class AssetsBalances {
    public Client indexerInstance = null;
    // utility function to connect to a node
    private Client connectToNetwork(){
        final String INDEXER_API_ADDR = "localhost";
        final int INDEXER_API_PORT = 8980;       
        IndexerClient indexerClient = new IndexerClient(INDEXER_API_ADDR, INDEXER_API_PORT); 
        return indexerClient;
    }
    public static void main(String args[]) throws Exception {
        AssetsBalances ex = new AssetsBalances();
        IndexerClient indexerClientInstance = (IndexerClient)ex.connectToNetwork();
        Long asset_id = Long.valueOf(440307);
        // searhes for asset greater than currencyGreaterThan
        Response<AssetBalancesResponse> response = indexerClientInstance
            .lookupAssetBalances(asset_id).execute();
        if (!response.isSuccessful()) {
            throw new Exception(response.message());
        } 
        JSONObject jsonObj = new JSONObject(response.body().toString());
        System.out.println("Asset Info: " + jsonObj.toString(2)); // pretty print json

    }
 }