/**
 * Copyright 2011-2012 eBusiness Information, Groupe Excilys (www.excilys.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.excilys.ebi.gatling.charts.component

import com.excilys.ebi.gatling.charts.config.ChartsFiles.GATLING_TEMPLATE_STATISTICS_COMPONENT_URL
import com.excilys.ebi.gatling.charts.template.PageTemplate.TEMPLATE_ENGINE
import com.excilys.ebi.gatling.core.result.reader.DataReader.NO_PLOT_MAGIC_VALUE
import com.excilys.ebi.gatling.core.util.FileHelper.TABULATION_SEPARATOR
import com.excilys.ebi.gatling.core.util.StringHelper.EMPTY

case class Statistics(name: String, total: Long, success: Long, failure: Long) {

	private def makePrintable(value: Long) = if (value != NO_PLOT_MAGIC_VALUE) value.toString else "-"

	def printableTotal: String = makePrintable(total)

	def printableSuccess: String = makePrintable(success)

	def printableFailure: String = makePrintable(failure)
}

case class RequestStatistics(name: String,
	path: String,
	numberOfRequestsStatistics: Statistics,
	minResponseTimeStatistics: Statistics,
	maxResponseTimeStatistics: Statistics,
	meanStatistics: Statistics,
	stdDeviationStatistics: Statistics,
	percentiles1: Statistics,
	percentiles2: Statistics,
	groupedCounts: Seq[(String, Int, Int)],
	meanNumberOfRequestsPerSecondStatistics: Statistics) {

	def mkString: String = new StringBuilder().append(path).append(TABULATION_SEPARATOR)
		.append(numberOfRequestsStatistics.total).append(TABULATION_SEPARATOR)
		.append(numberOfRequestsStatistics.success).append(TABULATION_SEPARATOR)
		.append(numberOfRequestsStatistics.failure).append(TABULATION_SEPARATOR)
		.append(minResponseTimeStatistics.total).append(TABULATION_SEPARATOR)
		.append(minResponseTimeStatistics.success).append(TABULATION_SEPARATOR)
		.append(minResponseTimeStatistics.failure).append(TABULATION_SEPARATOR)
		.append(maxResponseTimeStatistics.total).append(TABULATION_SEPARATOR)
		.append(maxResponseTimeStatistics.success).append(TABULATION_SEPARATOR)
		.append(maxResponseTimeStatistics.failure).append(TABULATION_SEPARATOR)
		.append(meanStatistics.total).append(TABULATION_SEPARATOR)
		.append(meanStatistics.success).append(TABULATION_SEPARATOR)
		.append(meanStatistics.failure).append(TABULATION_SEPARATOR)
		.append(stdDeviationStatistics.total).append(TABULATION_SEPARATOR)
		.append(stdDeviationStatistics.success).append(TABULATION_SEPARATOR)
		.append(stdDeviationStatistics.failure).append(TABULATION_SEPARATOR)
		.append(percentiles1.total).append(TABULATION_SEPARATOR)
		.append(percentiles1.success).append(TABULATION_SEPARATOR)
		.append(percentiles1.failure).append(TABULATION_SEPARATOR)
		.append(percentiles2.total).append(TABULATION_SEPARATOR)
		.append(percentiles2.success).append(TABULATION_SEPARATOR)
		.append(percentiles2.failure).append(TABULATION_SEPARATOR)
		.append(groupedCounts(0)._1).append(TABULATION_SEPARATOR)
		.append(groupedCounts(0)._2).append(TABULATION_SEPARATOR)
		.append(groupedCounts(0)._3).append(TABULATION_SEPARATOR)
		.append(groupedCounts(1)._1).append(TABULATION_SEPARATOR)
		.append(groupedCounts(1)._2).append(TABULATION_SEPARATOR)
		.append(groupedCounts(1)._3).append(TABULATION_SEPARATOR)
		.append(groupedCounts(2)._1).append(TABULATION_SEPARATOR)
		.append(groupedCounts(2)._2).append(TABULATION_SEPARATOR)
		.append(groupedCounts(2)._3).append(TABULATION_SEPARATOR)
		.append(groupedCounts(3)._1).append(TABULATION_SEPARATOR)
		.append(groupedCounts(3)._2).append(TABULATION_SEPARATOR)
		.append(groupedCounts(3)._3).append(TABULATION_SEPARATOR)
		.append(meanNumberOfRequestsPerSecondStatistics.total).append(TABULATION_SEPARATOR)
		.append(meanNumberOfRequestsPerSecondStatistics.success).append(TABULATION_SEPARATOR)
		.append(meanNumberOfRequestsPerSecondStatistics.failure)
		.toString
}

case class GroupStatistics(duration: Long)

class StatisticsTextComponent extends Component {

	def getHTMLContent: String = TEMPLATE_ENGINE.layout(GATLING_TEMPLATE_STATISTICS_COMPONENT_URL)

	val getJavascriptContent: String = EMPTY

	val getJavascriptFiles: Seq[String] = Seq.empty
}