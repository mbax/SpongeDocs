=========
Log Files
=========

Logfiles are an essential part when it comes to debugging your server and figuring what went wrong. This page contains
logfiles from SpongeForge and SpongeVanilla servers including short descriptions.

.. contents:: **Provided Logfiles**
   :depth: 2
   :local:

SpongeForge logfiles
====================

SpongeForge writes several logfiles to the ``/logs`` folder located inside your server's directory. 
As of Forge 1.12.2 - 14.23.4.2705 these are: 

1. ``debug.log``
#. ``latest.log``

debug.log
~~~~~~~~~

.. note::

 Only a few example lines are shown here. To read the full example log, follow this link:
 :download:`SpongeForge 1.12.2 - 7.1.0-BETA-3126 debug.log file 
 </files/logs/SpongeForge_1.12.2-2705-7.1.0-BETA-3136_debug.log.txt>`

.. code-block:: none

 [main/INFO] [FML]: Forge Mod Loader version 14.23.4.2705 for Minecraft 1.12.2 loading
 [main/INFO] [FML]: Java is Java HotSpot(TM) 64-Bit Server VM, version 1.8.0_162, running on Windows 10:amd64:10.0, installed at C:\Program Files\Java\jre1.8.0_162
 [main/DEBUG] [FML]: Java classpath at launch is:
 [main/DEBUG] [FML]:     forge-1.12.2-14.23.4.2705-universal.jar
 [main/DEBUG] [FML]: Java library path at launch is:
 [main/DEBUG] [FML]:     C:\ProgramData\Oracle\Java\javapath

The example log indicates that we're running:

* Forge 14.23.4.2705 (Version 2705)
* Java 8 64bit Update 162
* Windows 10 x64
* the ``directory`` Java was installed to (Line 4)

.. warning::

 In order to run Sponge, you **must** be running Java 8 Update 20 or above.
 Older builds or newer Java major versions (like 9 or 10) are not supported.

.. code-block:: none

 [main/DEBUG] [FML]: Examining for coremod candidacy spongeforge-1.12.2-2705-7.1.0-BETA-3136.jar
 [main/INFO] [FML]: Loading tweaker org.spongepowered.asm.launch.MixinTweaker from spongeforge-1.12.2-2705-7.1.0-BETA-3136.jar

This indicates that SpongeForge 3136 was found and loaded by Forge. For further help regarding the SpongeForge
naming scheme, have a look here: :doc:`../getting-started/implementations/spongeforge/`.

latest.log
~~~~~~~~~~

.. note::

 Only a few example lines are shown here. To read the full example log, follow this link:
 :download:`SpongeForge 1521 latest.log </files/logs/SpongeForge_1.12.2-2705-7.1.0-BETA-3136_latest.log.txt>`

This is the output that you would see in the Minecraft server GUI.


SpongeVanilla logfiles
======================

latest.log
~~~~~~~~~~

.. note::

 Only a few example lines are shown here. To read the full example log, follow this link:
 :download:`SpongeVanilla 1.12.2-7.1.0-BETA-54 latest.log 
 </files/logs/SpongeVanilla_1.12.2-7.1.0-BETA-54_latest.log.txt>`

This is the output that you would see in the Minecraft server GUI.

Reading logfiles
================

If you're unsure on how to read a common crashlog, you'll find help here, but first we need a crashlog. For this short
introduction we will just use an example crash from the :doc:`debugging` page:
:download:`Example crashlog of an outdated SpongeForge build </files/crashlogs/crashlog-sponge575-plugin750.txt>`.

.. code-block:: none

 WARNING: coremods are present:
 SpongeCoremod (sponge-1.8-1499-2.1DEV-575.jar)
 Contact their authors BEFORE contacting forge

The first thing you'll notice is a ``Warning`` that coremods are present. Nothing to worry about here, that's not an
error, just a warning to contact Sponge support, not Forge.

.. code-block:: none

 java.lang.NoClassDefFoundError: org/spongepowered/api/event/game/state/GameStartingServerEvent

A few lines below the actual error is found. In this case it's a ``NoClassDefFoundError`` If you're unsure what that
means, head over to our :doc:`debugging` page. If it's a common error, it will be listed there. If it isn't, you can
always ask on the forums for help! Make sure you provide the full crashlog.

Luckily your systems details are included at the bottom of the crashlog:

.. code-block:: none

 Minecraft Version: 1.8
 Operating System: Windows 8.1 (amd64) version 6.3
 Java Version: 1.8.0_51, Oracle Corporation
 Java VM Version: Java HotSpot(TM) 64-Bit Server VM (mixed mode), Oracle Corporation
 Memory: 515666256 bytes (491 MB) / 782761984 bytes (746 MB) up to 1847590912 bytes (1762 MB)
 JVM Flags: 0 total;
 IntCache: cache: 0, tcache: 0, allocated: 0, tallocated: 0
 FML: MCP v9.10 FML v8.0.99.99 Minecraft Forge 11.14.3.1521 5 mods loaded, 5 mods active
 States: 'U' = Unloaded 'L' = Loaded 'C' = Constructed 'H' = Pre-initialized 'I' = Initialized 'J' = Post-initialized 'A' = Available 'D' = Disabled 'E' = Errored
 UC	mcp{9.05} [Minecraft Coder Pack] (minecraft.jar)
 UC	FML{8.0.99.99} [Forge Mod Loader] (forge.jar)
 UC	Forge{11.14.3.1521} [Minecraft Forge] (forge.jar)
 UC	Sponge{1.8-1499-2.1DEV-575} [SpongeForge] (minecraft.jar)
 U	Core{unknown} [Core Plugin] (Core.jar)
 Loaded coremods (and transformers):
 SpongeCoremod (sponge-1.8-1499-2.1DEV-575.jar)

This indicates that

* Minecraft 1.8 with Forge 1521 was running on
* Java 8 Update 51 (64bit version) and that
* 2 additional mods were installed

    * SpongeForge 1.8-1499-2.1DEV-575 (which is build #575) and
    * Core

.. note::
 Please note that the other three installed mods (mcp, FML, Forge) are required on every Forge server and necessary to
 run properly.

Now the following assumptions can be made:

* maybe the plugin crashed the server
* SpongeForge doesn't match the Forge version: 1499 required, 1521 installed

If you want to know how to solve this, head over to our checklist on the :doc:`debugging` page.

Common errors
=============

Head over to :doc:`debugging` to read about common errors and exceptions.
