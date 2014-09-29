impels-server
=============

1. Install JDK 1.7
2. Install maven (thru Homebrew)
3. run "mvn compile" at the root of this project to build
4. In eclipse, run com.impels.web.ImpelsHTTPServer as a Java application
5. To test, try this URL using curl or in browser:

  (a) To see the example of getting article ids:
	http://localhost:9999/ArticleResource/articles/getIds?bleMajId=1234
	
	
  (b) To see the example of getting a local business's object:
	http://localhost:9999/ArticleResource/articles/getArticles?bleMajId=1234
