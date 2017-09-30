package jp.cordea.inco.repositories

import jp.cordea.inco.IncoApplication
import jp.cordea.inco.models.Rule
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async

object BlacklistRepository {

    private val ruleDao = IncoApplication.Database.ruleDao()

    fun getRules(): Deferred<List<Rule>> = async(CommonPool) {
        ruleDao.getRules()
    }

    fun updateRule(rule: Rule): Deferred<Unit> =
            async(CommonPool) {
                val oldRule = ruleDao.getRule(rule.regex)
                oldRule?.let {
                    ruleDao.updateRule(rule)
                    return@async
                }
                ruleDao.insertRule(rule)
            }

    fun deleteRule(rule: Rule): Deferred<Unit> =
            async(CommonPool) {
                ruleDao.deleteRule(rule)
            }

    fun deleteRule(regex: String): Deferred<Unit> =
            async(CommonPool) {
                ruleDao.deleteRuleByRegex(regex)
            }
}
