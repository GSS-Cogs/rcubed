
## Supporting Information and explanations

The key part of this is understanding the differences between the table field (previously partialy defined by the old columns.csv)
and the qb:structure field (previously defined by old components.csv).

1.) table - describes what things **are present in the dataset**.

2.) qb:structure - provides **the definition of those things**.

Beyond that, we'll consider each root csvw field in turn.


**please note** - this isn't exhaustive (you can put what you want in RDF, to a point) but this is intended
to be a practical minimum for representing a datacube in csvw such that it can be converted to RDF triples.


### context

Pure csvw boiler plate.


### dcat:distribution

The cube level metadata expressed as csvw. So what's the name? title? description? release data? etc of the thing as whole.

### tables

The tables described by the csvw. We've previously used this (and foreign keys) to descibe both the observations csv, and a csv for each codelist via the same csvw.

For this iteration, explicit codelist tables are not necessary, as the qb:codelist component is described directly in the dsd (see the qb:structure root field later in this document and the `qb:codeList` fields in the example).

For current purposes, it's describes exactly one table, which is the observation file.

### qb:structure

Outline the dsd (data structure definition) of the datacube.

The rule of thumb is that any column without a field of `"suppressOutput": true,` within the column definition (within tables - see above) represents a component.

The purposes of qb:structure is to define those components in terms of dimension, attribute and measure.

Standard dimensions follow a relatively simple pattern (see "ashe workplace or residence" and "ashe-hours-and-earnings" in the example).

Where a dimension is a variation or "sub concept", the `rdfs:subPropertyOf` field is used (see "ashe-sex" in the example). 

The measure type field is always attached as the "magic" qb:MeasureType (see example). 

All measure within that measureType are attached as simple measures and assumed to be within the measure type. See: `In the special case of using qb:measureType as the measure dimension, the set of allowed measures is assumed to be those measures declared within the DSD` from: [https://www.w3.org/TR/vocab-data-cube/#ref_qb_measureType]()

The properties relating the complementary observation status values to the Observation are mapped to AttributeProperty instances (see CV in the example). Please note, where the attribute applies to the dataset as a whole
rather than per-observation, if can be attached in simpler form (search `attached directly to the MeasureProperty` in [https://github.com/w3c/csvw/blob/gh-pages/examples/rdf-data-cube-example.md]())


### foreignKey

We may (or may not) need these anymore....    ¯\\_(ツ)_/¯

### primaryKey

Primary Key is principlally a validation tool , eg:

```
[
      "year",
      "geography",
      "ashe_statistics",
      "ashe_sex",
      "ashe_working_pattern",
      "ashe_hours_and_earnings",
      "ashe_workplace_or_residence",
      "measure_type"
    ]
```

means that every combination of choices in the above columns be represented **no more
than once** in the observation file (though no appearences is fine).