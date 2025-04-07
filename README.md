<h1>Graphql Learnings</h1></ br>
<p>
  Examples Include support for Multiple Query schemas, using Extend Type. </p>
  <p>
  Querying with Schema Mappings
  Mutations examples
<b>Learnings: </b> 
@QueryMapping --> When annotated on a method if the method name matches with Query Name no need to specify the Query name
@SchemaMapping --> to define the Schema type and to associate Schema type for specific fields
@MutationMapping --> Used to associate mutation methods based on the name same as Query mapping

There can be only one Type of Query object in the application, to define multiple schemas and associated queries
use the extend keyword. 


</p>
