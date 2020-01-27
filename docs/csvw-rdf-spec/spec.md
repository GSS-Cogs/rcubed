
### Specification

The purpose of this document is to outline the data required to generate a csvw representation of datacube as per the included worked example.

I'm also going to touch (very briefly) on the process to close, as it's somewhat required for this to all make sense.

### Datacube Level Metadata

*TODO - (when we've more time) less important, the obvious stuff really: title, description, issued etc.*



### Componets

Each datacube is made up of a mix of dimensions, attributes and measures. These are collectively known as **components**. 

Each component is defined by the following fields

**Please note** 

- *I've included examples to help explain, but some of these (url's in particular) are subject to change
as this piece of work advances.*

- *Remember this is **our** approach to component definitions, external sources eg [http://purl.org/linked-data/sdmx/2009/dimension#sex]() may differ in granularity*.

| Name        | Description                             | Required?   | Examples
| ------------| --------------------------------------- | ----------- |---
| type         | what type of component are we attaching as? | Y | dimension, attribute, measure.
| id           | the **UNIQUE** identifier of a component (typically a url) | Y | http://purl.org/linked-data/sdmx/2009/dimension#refArea, http://gss-data.org.uk/def/dimension/ashe-statistics
| rdfs:label | what it's called, plain english, user facing. | Y | "ASHE Sex", "Period"
| rdfs:range |  | Dimensions always have a range. Attributes *can*, Measures do not.
| skos:notation | The url-friendly name of somthing, ie style-like-this | Y | "paid-hours-worked", "ashe-sex"
| rdfs:subPropertyOf | If the property falls under a master dimension, eg ashe-sex under sex | N | 
| qb:codeList | the codelist describing the component| Y for dimensions and attributes, no for measures. | *to be decided...*

To clarify, while this table doesnt contain all the component information in the csvw example - it does contain *those fields that cannot or should not be derrived*.
### Process

I'm going to try and capture some of the process logic here while it's fresh. Discard as needed.

- The component data (as defined above) is going to live somewhere in rcubed.
- The codelists are going to live somewhere in rcubed too 

During/after the transformation *something* (be in a router, a local lookup file, a google table...whatever) is going to let us look at each column of the dataset in turn and get the relevant reference data (created from the above) ... which is then used to generate a csvw like the worked example .... which has all the info it needs to be converted to RDF.

The key part is seperating the column logic (what's in each column) from the component bit (defining those things) becase some components (eg dimensions) **will appear in multiple datasts** .




