# Download puzzles before 2023
# DAY?=$(shell date +%d)
DAY?=$(shell date +%-d)
DAY_WITH_PADDING=$(shell printf "%02d" ${DAY})
YEAR?=$(shell date +%Y)


new_day:
	@# ensure directories exist
	@mkdir -p app/src/main/java/aoc${YEAR}/day${DAY_WITH_PADDING}
	# @mkdir -p app/input/aoc${YEAR}
	@mkdir -p app/src/test/java/aoc${YEAR}

	@# create source file from template
ifeq (,$(wildcard app/src/main/java/aoc${YEAR}/day${DAY_WITH_PADDING}/Day${DAY_WITH_PADDING}.java))
	@cp templates/DayXX.java app/src/main/java/aoc${YEAR}/day${DAY_WITH_PADDING}/Day${DAY_WITH_PADDING}.java
	@sed -i 's/xx/${DAY}/g' app/src/main/java/aoc${YEAR}/day${DAY_WITH_PADDING}/Day${DAY_WITH_PADDING}.java
	@sed -i 's/XX/${DAY_WITH_PADDING}/g' app/src/main/java/aoc${YEAR}/day${DAY_WITH_PADDING}/Day${DAY_WITH_PADDING}.java
	@sed -i 's/YYYY/${YEAR}/g' app/src/main/java/aoc${YEAR}/day${DAY_WITH_PADDING}/Day${DAY_WITH_PADDING}.java
endif

	@# create test file from template
ifeq (,$(wildcard app/src/test/java/aoc${YEAR}/Day${DAY_WITH_PADDING}Test.java))
	@cp templates/DayXXTest.java app/src/test/java/aoc${YEAR}/Day${DAY_WITH_PADDING}Test.java
	@sed -i 's/xx/${DAY}/g' app/src/test/java/aoc${YEAR}/Day${DAY_WITH_PADDING}Test.java
	@sed -i 's/XX/${DAY_WITH_PADDING}/g' app/src/test/java/aoc${YEAR}/Day${DAY_WITH_PADDING}Test.java
	@sed -i 's/YYYY/${YEAR}/g' app/src/test/java/aoc${YEAR}/Day${DAY_WITH_PADDING}Test.java
endif

	@# create example input file
ifeq (,$(wildcard app/src/test/java/aoc${YEAR}/day${DAY_WITH_PADDING}_example.txt))
	@touch app/src/test/java/aoc${YEAR}/day${DAY_WITH_PADDING}_example.txt
	@curl -s "https://adventofcode.com/${YEAR}/day/${DAY}" | xmllint --html --xpath "//pre[1]/code/text()" - > "app/src/test/java/aoc${YEAR}/day${DAY_WITH_PADDING}_example.txt" 2> /dev/null || true
endif

	@# create input file (and download content if session cookie is given)
ifeq (,$(wildcard app/src/test/java/aoc${YEAR}/day${DAY_WITH_PADDING}.txt))
	@touch app/src/test/java/aoc${YEAR}/day${DAY_WITH_PADDING}.txt
ifneq (,$(wildcard session.txt))
	@curl -s -b $(shell head -n 1 ./session.txt 2> /dev/null) https://adventofcode.com/${YEAR}/day/${DAY}/input -o app/src/test/java/aoc${YEAR}/day${DAY_WITH_PADDING}.txt
endif
endif

	@# open files in IntelliJ
	@code app/src/test/java/aoc${YEAR}/day${DAY_WITH_PADDING}_example.txt app/src/test/java/aoc${YEAR}/Day${DAY_WITH_PADDING}Test.java app/src/test/java/aoc${YEAR}/day${DAY_WITH_PADDING}.txt app/src/main/java/aoc${YEAR}/day${DAY_WITH_PADDING}/Day${DAY_WITH_PADDING}.java > /dev/null

	@# finish by printing url to puzzle
	@echo "Files created for puzzle https://adventofcode.com/${YEAR}/day/${DAY}"
