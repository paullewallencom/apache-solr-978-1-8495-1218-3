require 'rubygems'
require 'rsolr'

solr = RSolr.connect :url => 'http://dev.fts-wizards.com:8983/solr'

response = solr.get 'select', :params => {
    :q => 'document',
    :start => 0,
    :rows => 10
  }

puts 'DOCUMENTS'
response["response"]["docs"].each{|doc| puts doc["id"] + ":" + doc["name"] }
