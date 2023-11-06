# A simple demo for reproducing suspected chinese file name issues for GraalVM Native Image

## Build
```
~/projects/simple-graalvm-native-image-issue-demo $ sbt graalvm-native-image:packageBin
```

also see: https://www.scala-sbt.org/sbt-native-packager/formats/graalvm-native-image.html

### Run and check
```
# run
~/projects/simple-graalvm-native-image-issue-demo $ ./target/graalvm-native-image/simple-graalvm-native-image-issue-demo
中文文件名测试!
java??????.conf
normal.txt
something_wrong_?????????????.txt

# check
~/projects/simple-graalvm-native-image-issue-demo $ tree /tmp/xyz
/tmp/xyz
├── java??????.conf
├── normal.txt
└── something_wrong_?????????????.txt

1 directory, 3 files

~/projects/simple-graalvm-native-image-issue-demo $ cat '/tmp/xyz/java??????.conf'
我可以吞下玻璃而不伤害身体
```

But pure Java runs OK:
```
~/projects/simple-graalvm-native-image-issue-demo (main) $ sbt stage

~/projects/simple-graalvm-native-image-issue-demo (main) $ rm -fr /tmp/xyz

~/projects/simple-graalvm-native-image-issue-demo (main) $ ./target/universal/stage/bin/simple-graalvm-native-image-issue-demo
中文文件名测试!
java生成的文件名.conf
normal.txt
something_wrong_我可以吞下玻璃而不伤害身体.txt

~/projects/simple-graalvm-native-image-issue-demo (main*) $ tree /tmp/xyz
/tmp/xyz
├── java生成的文件名.conf
├── normal.txt
└── something_wrong_我可以吞下玻璃而不伤害身体.txt

1 directory, 3 files
```

### Build outputs (contains the graalvm native image build parameters)
```
~/projects/simple-graalvm-native-image-issue-demo (main*) $ sbt graalvm-native-image:packageBin
[info] welcome to sbt 1.9.7 (N/A Java 19.0.2)
[info] loading global plugins from /home/weiwen/.sbt/1.0/plugins
[info] loading settings for project simple-graalvm-native-image-issue-demo-build from plugins.sbt ...
[info] loading project definition from /home/weiwen/projects/simple-graalvm-native-image-issue-demo/project
[info] loading settings for project root from build.sbt ...
[info] set current project to simple-graalvm-native-image-issue-demo (in build file:/home/weiwen/projects/simple-graalvm-native-image-issue-demo/)
[warn] sbt 0.13 shell syntax is deprecated; use slash syntax instead: Graalvm-native-image / packageBin
[info] Wrote /home/weiwen/projects/simple-graalvm-native-image-issue-demo/target/scala-3.3.1/simple-graalvm-native-image-issue-demo_3-0.0.1.pom
[info] Warning: The option '-H:Name=simple-graalvm-native-image-issue-demo' is experimental and must be enabled via '-H:+UnlockExperimentalVMOptions' in the future.
[info] Warning: Please re-evaluate whether any experimental option is required, and either remove or unlock it. The build output lists all active experimental options, including where they come from and possible alternatives. If you think an experimental option should be considered as stable, please file an issue.
[info] Apply jar:file:///nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/library-support.jar!/META-INF/native-image/com.oracle.svm/thirdparty/native-image.properties
[info] Apply jar:file:///nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/library-support.jar!/META-INF/native-image/com.oracle.svm/polyglot/native-image.properties
[info] Executing [
[info] HOME=/home/weiwen \
[info] LANG=en_US.UTF-8 \
[info] PATH=/nix/store/zlzz2z48s7ry0hkl55xiqp5a73b4mzrg-gcc-wrapper-12.3.0/bin:/nix/store/njjfhnvg90czp2jz37dkfyrhr97rvlii-openjdk-19.0.2+7/lib/openjdk/bin:/usr/local/bin:/run/wrappers/bin:/home/weiwen/.nix-profile/bin:/nix/profile/bin:/home/weiwen/.local/state/nix/profile/bin:/etc/profiles/per-user/weiwen/bin:/nix/var/nix/profiles/default/bin:/run/current-system/sw/bin \
[info] PWD=/home/weiwen/projects/simple-graalvm-native-image-issue-demo/target/graalvm-native-image \
[info] USE_NATIVE_IMAGE_JAVA_PLATFORM_MODULE_SYSTEM=true \
[info] /nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/bin/java \
[info] -XX:+UseParallelGC \
[info] -XX:+UnlockExperimentalVMOptions \
[info] -XX:+EnableJVMCI \
[info] -Dtruffle.TrustAllTruffleRuntimeProviders=true \
[info] -Dtruffle.TruffleRuntime=com.oracle.truffle.api.impl.DefaultTruffleRuntime \
[info] -Dgraalvm.ForcePolyglotInvalid=true \
[info] -Dgraalvm.locatorDisabled=true \
[info] -Dsubstratevm.HostLibC=glibc \
[info] -Dsubstratevm.IgnoreGraalVersionCheck=true \
[info] --add-exports=java.base/com.sun.crypto.provider=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.access=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.event=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.loader=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.logger=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.misc=org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile,org.graalvm.nativeimage.pointsto \
[info] --add-exports=java.base/jdk.internal.module=org.graalvm.nativeimage.base,org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.org.objectweb.asm=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.perf=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.platform=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.ref=org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile \
[info] --add-exports=java.base/jdk.internal.reflect=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.util=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.vm.annotation=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal.vm=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/jdk.internal=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.invoke.util=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.net.www=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.net=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.nio.ch=org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile \
[info] --add-exports=java.base/sun.reflect.annotation=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.reflect.generics.factory=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.reflect.generics.reflectiveObjects=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.reflect.generics.repository=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.reflect.generics.scope=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.reflect.generics.tree=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.security.jca=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.security.provider=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.security.ssl=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.security.util=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.security.x509=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.text.spi=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.util.calendar=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.util.cldr=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.util.locale.provider=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.util.locale=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.util.resources=org.graalvm.nativeimage.builder \
[info] --add-exports=java.base/sun.util=org.graalvm.nativeimage.builder \
[info] --add-exports=java.management/com.sun.jmx.mbeanserver=org.graalvm.nativeimage.builder \
[info] --add-exports=java.management/sun.management=org.graalvm.nativeimage.builder,org.graalvm.nativeimage.pointsto \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.aarch64=jdk.internal.vm.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.amd64=jdk.internal.vm.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.code.site=jdk.internal.vm.compiler,org.graalvm.nativeimage.builder \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.code.stack=jdk.internal.vm.compiler,org.graalvm.nativeimage.builder \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.code=jdk.internal.vm.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile,org.graalvm.nativeimage.pointsto,org.graalvm.truffle.compiler \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.common=jdk.internal.vm.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.pointsto \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.hotspot.aarch64=jdk.internal.vm.compiler \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.hotspot.amd64=jdk.internal.vm.compiler \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.hotspot=jdk.internal.vm.compiler,org.graalvm.nativeimage.builder \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.meta=jdk.internal.vm.compiler,org.graalvm.nativeimage.base,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.objectfile,org.graalvm.nativeimage.pointsto,org.graalvm.truffle.compiler \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.runtime=jdk.internal.vm.compiler,org.graalvm.nativeimage.builder,org.graalvm.nativeimage.pointsto \
[info] --add-exports=jdk.internal.vm.ci/jdk.vm.ci.services=jdk.internal.vm.compiler,org.graalvm.nativeimage.builder \
[info] --add-exports=jdk.jfr/jdk.jfr.events=org.graalvm.nativeimage.builder \
[info] --add-exports=jdk.jfr/jdk.jfr.internal.jfc=org.graalvm.nativeimage.builder \
[info] --add-exports=jdk.jfr/jdk.jfr.internal=org.graalvm.nativeimage.builder \
[info] --add-exports=jdk.management/com.sun.management.internal=org.graalvm.nativeimage.builder \
[info] -XX:+UseJVMCINativeLibrary \
[info] -Xss10m \
[info] -XX:MaxRAMPercentage=47.52084150058196 \
[info] -XX:GCTimeRatio=9 \
[info] -XX:+ExitOnOutOfMemoryError \
[info] -Djava.awt.headless=true \
[info] '-Dorg.graalvm.vendor=GraalVM Community' \
[info] -Dorg.graalvm.vendorurl=https://www.graalvm.org/ \
[info] '-Dorg.graalvm.vendorversion=GraalVM CE 21.0.1+12.1' \
[info] -Dorg.graalvm.version=23.1.1 \
[info] -Dcom.oracle.graalvm.isaot=true \
[info] -Djava.system.class.loader=com.oracle.svm.hosted.NativeImageSystemClassLoader \
[info] -Xshare:off \
[info] -Djdk.internal.lambda.disableEagerInitialization=true \
[info] -Djdk.internal.lambda.eagerlyInitialize=false \
[info] -Djava.lang.invoke.InnerClassLambdaMetafactory.initializeLambdas=false \
[info] -Dfile.encoding=UTF8 \
[info] -Dfile.encoding=UTF8 \
[info] -Dsun.stdout.encoding=UTF-8 \
[info] --add-modules=ALL-DEFAULT \
[info] --module-path \
[info] /nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/builder/native-image-base.jar:/nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/builder/objectfile.jar:/nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/builder/svm.jar:/nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/builder/pointsto.jar \
[info] --module \
[info] org.graalvm.nativeimage.builder/com.oracle.svm.hosted.NativeImageGeneratorRunner \
[info] -watchpid \
[info] 687961 \
[info] -imagecp \
[info] /home/weiwen/projects/simple-graalvm-native-image-issue-demo/target/scala-3.3.1/simple-graalvm-native-image-issue-demo_3-0.0.1.jar:/home/weiwen/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala3-library_3/3.3.1/scala3-library_3-3.3.1.jar:/home/weiwen/.cache/coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/scala-library/2.13.10/scala-library-2.13.10.jar \
[info] -imagemp \
[info] /nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/library-support.jar \
[info] -H:CLibraryPath=/nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/clibraries/linux-amd64 \
[info] -H:Path@driver=/home/weiwen/projects/simple-graalvm-native-image-issue-demo/target/graalvm-native-image \
[info] -H:CLibraryPath@user=/nix/store/gqghjch4p1s69sv4mcjksb2kb65rwqjy-glibc-2.38-23/lib \
[info] -H:CLibraryPath@user=/nix/store/ynxb31sa1sgcfj49mkn6yh63rgw6knd1-zlib-1.3-static/lib \
[info] -H:CLibraryPath@user=/nix/store/kz37vq6iv53kzn3ak8vvczam9hdl9ky8-glibc-2.38-23-static/lib \
[info] -H:Name@user=simple-graalvm-native-image-issue-demo \
[info] -H:+StaticExecutable@user+api \
[info] -H:+UnlockExperimentalVMOptions@user \
[info] -H:FallbackThreshold@user+api=0 \
[info] -H:-UnlockExperimentalVMOptions@user \
[info] '-H:Class@explicit main-class=simple.main' \
[info] -H:ImageBuildID@driver=fd24d39a-7ea5-3350-6588-1195056d1cab \
[info] '-H:Features@jar:file:///nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/library-support.jar!/META-INF/native-image/com.oracle.svm/thirdparty/native-image.properties+api=com.oracle.svm.thirdparty.gson.GsonFeature' \
[info] '-H:Features@jar:file:///nix/store/0gl0rq2xcjmz3izgw4n1l416n1f9kxqy-graalvm-ce-21.0.1/lib/svm/library-support.jar!/META-INF/native-image/com.oracle.svm/polyglot/native-image.properties+api=com.oracle.svm.polyglot.groovy.GroovyIndyInterfaceFeature,com.oracle.svm.polyglot.scala.ScalaFeature'
[info] ]
[info] ========================================================================================================================
[info] GraalVM Native Image: Generating 'simple-graalvm-native-image-issue-demo' (static executable)...
[info] ========================================================================================================================
[info] For detailed information and explanations on the build output, visit:
[info] https://github.com/oracle/graal/blob/master/docs/reference-manual/native-image/BuildOutput.md
[info] ------------------------------------------------------------------------------------------------------------------------
[info] [1/8] Initializing...                                                                                    (2.4s @ 0.17GB)
[info]  Java version: 21.0.1+12, vendor version: GraalVM CE 21.0.1+12.1
[info]  Graal compiler: optimization level: 2, target machine: x86-64-v3
[info]  C compiler: gcc (unknown, x86_64, 12.3.0)
[info]  Garbage collector: Serial GC (max heap size: 80% of RAM)
[info]  2 user-specific feature(s):
[info]  - com.oracle.svm.polyglot.scala.ScalaFeature
[info]  - com.oracle.svm.thirdparty.gson.GsonFeature
[info] ------------------------------------------------------------------------------------------------------------------------
[info]  1 experimental option(s) unlocked:
[info]  - '-H:Name' (alternative API option(s): -o simple-graalvm-native-image-issue-demo; origin(s): command line)
[info] ------------------------------------------------------------------------------------------------------------------------
[info] Build resources:
[info]  - 26.49GB of memory (42.2% of 62.71GB system memory, determined at start)
[info]  - 32 thread(s) (100.0% of 32 available processor(s), determined at start)
[info] [2/8] Performing analysis...  [******]                                                                   (4.0s @ 0.63GB)
[info]     3,710 reachable types   (72.8% of    5,095 total)
[info]     4,162 reachable fields  (50.2% of    8,289 total)
[info]    17,169 reachable methods (38.9% of   44,087 total)
[info]     1,214 types,   107 fields, and   697 methods registered for reflection
[info]        57 types,    55 fields, and    52 methods registered for JNI access
[info]         4 native libraries: dl, pthread, rt, z
[info] [3/8] Building universe...                                                                               (1.0s @ 0.44GB)
[info] [4/8] Parsing methods...      [*]                                                                        (0.6s @ 0.62GB)
[info] [5/8] Inlining methods...     [***]                                                                      (0.5s @ 0.56GB)
[info] [6/8] Compiling methods...    [**]                                                                       (3.6s @ 0.44GB)
[info] [7/8] Layouting methods...    [*]                                                                        (1.0s @ 0.43GB)
[info] [8/8] Creating image...       [*]                                                                        (1.4s @ 0.42GB)
[info]    5.69MB (36.37%) for code area:     9,616 compilation units
[info]    8.58MB (54.90%) for image heap:  102,816 objects and 47 resources
[info]    1.36MB ( 8.72%) for other data
[info]   15.63MB in total
[info] ------------------------------------------------------------------------------------------------------------------------
[info] Top 10 origins of code area:                                Top 10 object types in image heap:
[info]    4.17MB java.base                                            1.76MB byte[] for java.lang.String
[info]  976.77kB svm.jar (Native Image)                               1.70MB byte[] for code metadata
[info]  149.54kB scala-library-2.13.10.jar                            1.01MB java.lang.String
[info]  117.02kB java.logging                                       861.03kB java.lang.Class
[info]   65.00kB org.graalvm.nativeimage.base                       351.31kB heap alignment
[info]   47.59kB jdk.proxy1                                         318.83kB com.oracle.svm.core.hub.DynamicHubCompanion
[info]   45.84kB jdk.proxy3                                         278.78kB byte[] for general heap data
[info]   27.06kB jdk.internal.vm.ci                                 246.94kB java.util.HashMap$Node
[info]   22.06kB org.graalvm.collections                            226.80kB java.lang.Object[]
[info]   11.42kB jdk.proxy2                                         196.52kB java.lang.String[]
[info]   18.41kB for 6 more packages                                  1.69MB for 993 more object types
[info] ------------------------------------------------------------------------------------------------------------------------
[info] Recommendations:
[info]  INIT: Adopt '--strict-image-heap' to prepare for the next GraalVM release.
[info]  HEAP: Set max heap for improved and more predictable memory usage.
[info]  CPU:  Enable more CPU features with '-march=native' for improved performance.
[info] ------------------------------------------------------------------------------------------------------------------------
[info]                         0.7s (4.6% of total time) in 51 GCs | Peak RSS: 1.34GB | CPU load: 15.01
[info] ------------------------------------------------------------------------------------------------------------------------
[info] Produced artifacts:
[info]  /home/weiwen/projects/simple-graalvm-native-image-issue-demo/target/graalvm-native-image/simple-graalvm-native-image-issue-demo (executable)
[info] ========================================================================================================================
[info] Finished generating 'simple-graalvm-native-image-issue-demo' in 14.9s.
[success] Total time: 16 s, completed 2023年11月6日 19:53:34
```
