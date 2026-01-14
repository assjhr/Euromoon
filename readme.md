# Euromoon – Java
# Projectbeschrijving

Euromoon is een treinmaatschappij die internationale treinreizen binnen Europa organiseert.
In dit project werd een Java command line applicatie ontwikkeld als proof of concept om:

- personen en personeel te beheren
- treinen en reizen aan te maken
- tickets te verkopen
- te controleren dat een trein niet overboekt wordt
Het project is opgebouwd volgens objectgeoriënteerde principes.

# Structuur van het project
- Personen (personen)
- Persoon (abstracte superklasse)
- Passagier
- Personeel
- PersoneelTypes (BESTUURDER, STEWARD, …)
- Certificaten

Elke persoon heeft:
- naam
- achternaam
- rijksregisternummer
- geboortedatum
- Personeel heeft daarnaast ook een type en certificaten.

Treinen (treinen)
- Locomotief
- Trein
- Wagon
- TypeLocomotief
- Klasse (EERSTE_KLASSE, TWEEDE_KLASSE)

Een trein:

- heeft exact één locomotief
- bevat automatisch het juiste aantal wagons
- verdeelt eerste en tweede klasse over wagons
- heeft een vaste totale capaciteit van 80 plaatsen
- De capaciteit per klasse wordt berekend op basis van het aantal wagons per klasse.

Reizen (reizen)
- Station
- NodigePersoneel
- Reis

Een reis:
- gaat van een vertrekstation naar een bestemmingsstation
- heeft een vertrektijd
- gebruikt één trein
- heeft verplicht personeel nodig:
- minstens 1 bestuurder
- minstens 3 stewards

Ticket

Een ticket bevat:
- de passagier
- de reis
- de klasse (eerste of tweede klasse)
- Tickets kunnen niet verkocht worden als de trein vol is in die klasse.

Ticketverkoop & validatie
- Tickets worden verkocht via de Reis-klasse
- Voor elke verkoop wordt gecontroleerd of er nog plaats is
- Indien de trein vol is, wordt een IllegalStateException gegooid
- In de Main-klasse wordt dit opgevangen met try / catch

# Foutafhandeling
Dit project gebruikt design by contract:
- In de logica (bv. Reis, NodigePersoneel) worden fouten gegooid met throw
- In de Main-klasse worden fouten opgevangen met try / catch
- Zo crasht de applicatie niet en blijft ze correct functioneren

# Main (testprogramma)

- De Main-klasse toont:
- het aanmaken van stations, treinen en reizen
- het toewijzen van personeel
- het verkopen van tickets
- een overzicht van verkochte tickets
- correcte foutafhandeling wanneer een trein vol is

# Bronnen

Tijdens dit project werd ChatGPT en de cursus op Canvas gebruikt als ondersteunend hulpmiddel:
- om objectgeoriënteerde structuren te bespreken
- om Java-concepten beter te begrijpen
- om code te verbeteren en fouten te analyseren

Alle code werd zelf geschreven en begrepen.