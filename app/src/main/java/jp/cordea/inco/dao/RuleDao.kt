package jp.cordea.inco.dao

import android.arch.persistence.room.*
import jp.cordea.inco.models.Rule

@Dao
interface RuleDao {

    @Query("SELECT * FROM rule")
    fun getRules(): List<Rule>

    @Query("SELECT * FROM rule WHERE regex = :regex LIMIT 1")
    fun getRule(regex: String): Rule?

    @Query("DELETE FROM rule WHERE regex = :regex")
    fun deleteRuleByRegex(regex: String)

    @Insert
    fun insertRule(rule: Rule)

    @Update
    fun updateRule(rule: Rule)

    @Delete
    fun deleteRule(rule: Rule)
}
