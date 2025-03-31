# frozen_string_literal: true

require './player'

class Team
  attr_accessor :name, :dps, :tank, :support

  def initialize(name)
    @name = name
    @dps = Player.new("#{name}DPS")
    @tank = Player.new("#{name}Tank")
    @support = Player.new("#{name}Support")
  end

  def team
    "TEAM: #{@name}
    #{@dps.name}: dps #{@dps.dps_proficiency}, tank #{@dps.tank_proficiency}, support #{@dps.support_proficiency}
    #{@tank.name}: dps #{@tank.dps_proficiency}, tank #{@tank.tank_proficiency}, support #{@tank.support_proficiency}
    #{@support.name}: dps #{@support.dps_proficiency}, tank #{@support.tank_proficiency}, support #{@support.support_proficiency}"
  end
end
