require 'rubygems'
require 'rsolr'

solr = RSolr.connect :url => 'http://dev.fts-wizards.com:8983/solr/'

documents = [
	{:id => 1, :name => 'Document 1'}, 
	{:id => 2, :name => 'Document 2'}
]

solr.add documents

solr.update :data => '<commit/>'
solr.update :data => '<optimize/>'