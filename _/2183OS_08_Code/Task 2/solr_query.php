<?php
require_once 'Apache/Solr/Service.php';

$solr = new Apache_Solr_Service("dev.fts-wizards.com", "8983", "/solr");

$response = $solr->search("document", 0, 10, null);

echo "DOCUMENTS:\n";
foreach ($response->response->docs as $doc) {
	echo $doc->id . ":" . $doc->name . "\n";
}
?>