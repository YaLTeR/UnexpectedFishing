UnexpectedFishing
=================

A Bukkit plugin, that makes fishing slightly more... unexpected.

####How chances work
The main chance setting sets the chance of some special event occuring. It is measured in percents. The default value is 10, which means 10%.
The chances for the different actions work as follows: the chance for every action is <chance for that action set in config> / <sum of chances of every action>. By default the chances are set in such a way that their sum equals to 100. Let's take the "trash" chance. It is equal to 20. So the chance of this action occuring as a special action is 20 / 100, or 20%.

####Commands
/ufish reload-config - Reloads the configuration file.
/ufish debug-mode <true | false> - If set to true, enables the debug mode, otherwise disables.
/ufish debug-action <ID> - Sets the action ID to the given one.

####Build
The plugin requires the Bukkit API to be linked in order to be built.
