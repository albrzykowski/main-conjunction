main-conjunction
===========

[![Clojars Project](https://clojars.org/re.blacksqua/main-conjunction/latest-version.svg)](https://clojars.org/re.blacksqua/main-conjunction)

[![Build Status](https://travis-ci.org/albrzykowski/main-conjunction.svg?branch=master)](https://travis-ci.org/albrzykowski/main-conjunction)
[![codecov](https://codecov.io/gh/albrzykowski/main-conjunction/branch/master/graph/badge.svg)](https://codecov.io/gh/albrzykowski/main-conjunction)

Project is inspired by [Automatyczne dowodzenie twierdzeń](https://www.researchgate.net/publication/44008571_Automatyczne_dowodzenie_twierdzen) authored by Polish mathematician [Zdzisław Pawlak](https://pl.wikipedia.org/wiki/Zdzisław_Pawlak). More about main conjunction you can find here: [Klasyczny rachunek zdań](https://repozytorium.umk.pl/bitstream/handle/item/2975/M.%20Nasieniewski%2C%20Klasyczny%20rachunek%20zdań.%20z%20M.%20Urchsem%20i%20S.%20Kwiatkowskim%2C%20Wyd%2C%20UMK%2C%20Toruń%201997%20CALA%20KSIAZKA.pdf?sequence=1).

## Main conjunction

How formula element's value is calculated:
1. `F(x_<sub>1</sub>) = 0`.
2. `F(x_<sub>i+1</sub>) = F(x_<sub>i</sub>) + 1` when `F(x_<sub>i</sub>) IN {"(" "a" "b" "c" ...}`.
3. `F(x_<sub>i+1</sub>) = F(x_<sub>i</sub>) - 1` when `F(x_<sub>i</sub>) IN {")" "OR" "AND"  "IMPL" "IFF"}`.
4. `F(x_<sub>i+1</sub>) = F(x_<sub>i</sub>)` when `F(x_<sub>i</sub>) IN {"NOT"}`.

Main conjunction (except first and last bracket) has value `0`.

## Examples

### Example 1.

Let `f` be the formula: `( p AND ( q OR r ) )`

We can present formula `f` as a tree: 

      ( p AND ( q OR r ) )
     /                    \
    p                      ( q OR r )
                          /          \
                         q            r

In this example main conjunction is `AND`. The calculated values for the formula is: `[0 1 0 1 2 1 2 1 0]`.

### Example 2.
Let `f` be the formula: `( ( NOT ( p OR q ) OR q ) AND ( p AND NOT r ) )`

Table presenting calculated values:

|   | ( | ( | NOT | ( | p | OR | q | ) | OR | q | ) | AND | (  | p | AND | NOT | r | ) | ) | 
|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|---|
|| 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 | 10 | 11 | 12 | 13 | 14 | 15 | 16 | 17 | 18 | 19|
|| 0 | 1 | 1 | 2 | 3 | 2 | 3 | 2 | 1 | 2 | 1 | 0 | 1 | 2 | 1 | 1 | 2 | 1 | 0 |