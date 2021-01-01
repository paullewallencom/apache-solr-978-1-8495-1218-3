<?php
require_once 'Apache/Solr/Service.php';

$solr = new Apache_Solr_Service("dev.fts-wizards.com", "8983", "/solr");

$docs = array(
    'doc1' => array(
      'id' => '1',
      'name' => 'Document one',
    ),
    'doc2' => array(
      'id' => 2,
      'name' => 'Document two',
    ),
  );
    
  $documents = array();
  foreach ( $docs as $item => $fields ) {
    $solrdoc = new Apache_Solr_Document();
    foreach ( $fields as $key => $value ) {
        $solrdoc->$key = $value;
    }
    $documents[] = $solrdoc;
  }
    
  try {
    $solr->addDocuments( $documents );
    $solr->commit();
    $solr->optimize();
  }
  catch ( Exception $ex ) {
    echo $ex->getMessage();
  }
?>