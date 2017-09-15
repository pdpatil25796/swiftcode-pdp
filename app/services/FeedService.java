package services;

import data.FeedResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import play.libs.ws.WS;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;

public class FeedService {
    public FeedResponse getFeedResponse(String keyword) {
        FeedResponse feedResponseObject = new FeedResponse();
        try {
            WSRequest feedQuery = WS.url("http://www.news.google.com/news");
            CompletionStage<WSResponse> responsePromise = feedQuery
                    .setQueryParameter("q", keyword)
                    .setQueryParameter("output", "rss")
                    .get();

            Document feedResponse = responsePromise.thenApply(WSResponse::asXml).toCompletableFuture().get();

            Node item = feedResponse.getFirstChild().getChildNodes().item(9);
            feedResponseObject.title = item.getChildNodes().item(0).getNodeValue();
            feedResponseObject.description = item.getChildNodes().item(4).getNodeValue();
            feedResponseObject.pubDate = item.getChildNodes().item(3).getNodeValue();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return feedResponseObject;
    }
}